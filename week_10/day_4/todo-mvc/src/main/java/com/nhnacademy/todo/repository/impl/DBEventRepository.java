package com.nhnacademy.todo.repository.impl;

import com.nhnacademy.todo.domain.Event;
import com.nhnacademy.todo.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Primary//우선순위를 높여 주입 하도록함.
@Repository
public class DBEventRepository implements EventRepository {
    private final DataSource dataSource;

    public DBEventRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Event save(Event event) {
        Connection connection;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Event result = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO event(subject, user_id, event_at, created_at) VALUES (?,?,?,now())";
            //sql injection
            int index = 0;
            psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            psmt.setString(++index, event.getSubject());
            psmt.setString(++index, event.getUserId());
            psmt.setObject(++index, event.getEventAt());
            psmt.executeUpdate();
            connection.commit();

            //select_last_id()
            rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                long eventId = rs.getLong(1);
                result = getEvent(eventId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(psmt, rs);
        }
        return result;
    }

    private void close(PreparedStatement psmt, ResultSet rs) {
        if (Objects.nonNull(psmt)) {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (Objects.nonNull(rs)) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Event event) {

    }

    @Override
    public void deleteById(String userId, long eventId) {

    }

    @Override
    public Event getEvent(String userId, long eventId) {
        return null;
    }

    @Override
    public Event getEvent(long eventId) {
        Connection connection;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Event result = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String sql = "SELECT id,subject, user_id,event_at, created_at FROM event WHERE id = ?";
            //sql injection
            psmt = connection.prepareStatement(sql);
            psmt.setLong(1, eventId);

            rs = psmt.executeQuery();

            while (rs.next()) {
                Event event = new Event(rs.getString("user_id"), rs.getString("subject"), rs.getDate("event_at").toLocalDate());
                event.setId(rs.getLong("id"));
                result = event;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            close(psmt, rs);
        }
        return result;
    }

    @Override
    public List<Event> findAllByDay(String userId, int year, int month, int day) {
        return null;
    }

    @Override
    public List<Event> findAllByMonth(String userId, int year, int month) {
        return null;
    }

    @Override
    public long countByUserIdAndEventAt(String userId, LocalDate targetDate) {
        return 0;
    }

    @Override
    public void deletebyDaily(String userId, LocalDate targetDate) {

    }

    @Override
    public void init() {

    }
}
