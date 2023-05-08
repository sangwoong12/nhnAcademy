package com.nhnacademy.board.mapper.user;

import com.nhnacademy.board.config.RootConfig;
import com.nhnacademy.board.item.user.User;
import com.nhnacademy.board.item.user.UserImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = RootConfig.class
)
@Transactional
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    private final static int TOTAL_SIZE = 14;
    private final static int FIRST_PAGE_SIZE = 10;

    @Test
    @DisplayName("tester가 잘 추가되었는지 테스트")
    void add() {
        // given
        User user = new UserImpl("tester", "tester", "12345", null);

        // when
        userMapper.add(user);
        User addedUser = userMapper.getUser(user.getId());

        // then
        // AssertThat .isNotZero 만으로 추가되었다고 판단하기가 어렵다 생각하여 getUser를 사용하여 실제로 들어갔는지 테스트.
        assertThat(addedUser.getId()).isNotNull();
        assertThat(addedUser.getId()).isEqualTo(user.getId());
        assertThat(addedUser.getName()).isEqualTo(user.getName());
        assertThat(addedUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(addedUser.getProfileFileName()).isEqualTo(user.getProfileFileName());

    }

    @Test
    @DisplayName("test1가 잘 수정되었는지 테스트")
    void modify() {
        // given
        User user = new UserImpl("test1", "tester", "12345", null);
        // when
        userMapper.modify(user);
        User addedUser = userMapper.getUser(user.getId());

        // then
        assertThat(addedUser.getId()).isNotNull();
        assertThat(addedUser.getId()).isEqualTo(user.getId());
        assertThat(addedUser.getName()).isEqualTo(user.getName());
        assertThat(addedUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(addedUser.getProfileFileName()).isEqualTo(user.getProfileFileName());
    }

    @Test
    @DisplayName("test11가 삭제가 되는지 테스트")
    void remove() {
        // given
        String id = "test11";
        // when
        userMapper.remove(id);
        // then
        User getUser = userMapper.getUser("test11");
        assertThat(getUser).isNull();
    }

    @Test
    @DisplayName("test1의 유저를 찾고 데이터가 일치하는지 테스트")
    void getUser() {
        // given
        String id = "test1";

        // when
        User user = userMapper.getUser(id);

        // then
        assertThat(user.getId()).isEqualTo("test1");
        assertThat(user.getName()).isEqualTo("테스터1");
        assertThat(user.getPassword()).isEqualTo("12345");
        assertThat(user.getProfileFileName()).isNull();
    }

    @Test
    @DisplayName("모든 유저를 찾은 이후 첫번째 유저가 test1가 맞는지 테스트")
    void getUsers() {
        // when
        List<User> users = userMapper.getUsers();

        // then
        assertThat(users.get(1).getId()).isEqualTo("test1");
        assertThat(users.get(1).getName()).isEqualTo("테스터1");
        assertThat(users.get(1).getPassword()).isEqualTo("12345");
        assertThat(users.get(1).getProfileFileName()).isNull();
    }

    @Test
    @DisplayName("test1 의 유저가 존재하는지 테스트")
    void existById() {
        // given
        String id = "test1";

        // when
        boolean b = userMapper.existById(id);

        // then
        assertThat(b).isTrue();
    }

    @Test
    @DisplayName("총 갯수가 일치 하는지 테스트")
    void getTotalCount() {
        // when
        int totalCount = userMapper.getTotalCount();
        // given
        // 이 테스트의 경우 실제 data
        assertThat(totalCount).isEqualTo(TOTAL_SIZE);
    }

    @Test
    @DisplayName("첫 페이지에 데이터가 10개 존재하는지 테스트")
    void getPagedUsers() {
        // when
        List<User> pagedUsers = userMapper.getPagedUsers(10, 1);

        //given
        assertThat(pagedUsers).hasSize(FIRST_PAGE_SIZE);
    }
}