package com.nhnacademy.flight.member;

import com.nhnacademy.flight.item.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "memberServlet", value = "/member")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/module12", "root", "");
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM Members LIMIT 20 OFFSET ?");
                 PreparedStatement pageStatement = connection.prepareStatement("SELECT COUNT(*) FROM Members")) {
                List<Member> list = new ArrayList<>();
                int pageNum = 1;

                //pageNum 올바른지 확인.
                if (req.getParameter("pageNum") != null && req.getParameter("pageNum").matches("^[0-9]+$")) {
                    pageNum = Integer.parseInt(req.getParameter("pageNum"));
                }

                statement.setInt(1, pageNum * 20);

                ResultSet resultSet = statement.executeQuery();
                ResultSet pageResultSet = pageStatement.executeQuery();
                //데이터
                while (resultSet.next()) {
                    list.add(new Member(resultSet.getInt("Id"),
                            resultSet.getString("Name"),
                            resultSet.getString("City")));
                }
                //페이지 최대 값
                pageResultSet.next();
                int anInt = pageResultSet.getInt(1);
                int maxPageNum = anInt / 20 != 0 ? anInt / 20 + 1 : anInt / 20;

                int startPageNum;

                if (pageNum < 5) {
                    startPageNum = 0;
                } else if (pageNum > maxPageNum - 10) {
                    startPageNum = maxPageNum - 10;
                } else {
                    startPageNum = pageNum - 5;
                }

                req.setAttribute("startPageNum", startPageNum);
                req.setAttribute("list", list);
                resultSet.close();
                pageResultSet.close();

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("member.jsp");
                requestDispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
