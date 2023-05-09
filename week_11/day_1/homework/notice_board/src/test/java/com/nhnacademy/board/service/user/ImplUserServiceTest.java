package com.nhnacademy.board.service.user;

import com.nhnacademy.board.domain.LoginRequest;
import com.nhnacademy.board.exception.ExistingUserIdException;
import com.nhnacademy.board.exception.NotFoundUserException;
import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.PageImpl;
import com.nhnacademy.board.item.user.User;
import com.nhnacademy.board.item.user.UserImpl;
import com.nhnacademy.board.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ImplUserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new ImplUserService(userRepository);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("유저가 존재할때 조회 테스트")
    void getUser() {
        // given
        String userId = "test";
        User user = new UserImpl(userId, "tester", "12345", null);

        given(userRepository.existById(userId)).willReturn(true);
        given(userRepository.getUser(userId)).willReturn(user);

        // when
        User getUser = userService.getUser(userId);

        // then
        assertThat(getUser).isEqualTo(user);
    }

    @Test
    @DisplayName("유저가 존재하지 않을때 조회 예외 테스트")
    void getUser2() {
        // given
        String userId = "test";

        given(userRepository.existById(userId)).willReturn(false);

        // when, then
        assertThrows(NotFoundUserException.class, () -> userService.getUser(userId));
    }

    @Test
    @DisplayName("유저가 존재할때 삭제 테스트")
    void deleteUser() {
        // given
        String userId = "test";
        User user = new UserImpl(userId, "tester", "12345", null);

        given(userRepository.existById(userId)).willReturn(true);
        given(userRepository.remove(userId)).willReturn(user);

        // when
        User deleteUser = userService.deleteUser(userId);

        // then
        assertThat(deleteUser).isEqualTo(user);
    }

    @Test
    @DisplayName("유저가 존재하지않을때 삭제 예외 테스트")
    void deleteUser2() {
        // given
        String userId = "test";

        given(userRepository.existById(userId)).willReturn(false);

        // when, then
        assertThrows(NotFoundUserException.class, () -> userService.deleteUser(userId));
    }

    @Test
    @DisplayName("존재하지 않는 아이디로 추가시 동작여부 확인")
    void addUser() {
        // given
        String userId = "test";
        User user = new UserImpl(userId, "tester", "12345", null);
        given(userRepository.existById(userId)).willReturn(false);

        // when
        userService.addUser(user);

        // then
        verify(userRepository).add(user);
    }

    @Test
    @DisplayName("존재하는 아이디로 추가 예외 확인")
    void addUser2() {
        // given
        String userId = "test";
        User user = new UserImpl(userId, "tester", "12345", null);

        given(userRepository.existById(userId)).willReturn(true);

        // when, then
        assertThrows(ExistingUserIdException.class, () -> userService.addUser(user));
    }

    @Test
    @DisplayName("존재하는 아이디로 수정 동작 확인")
    void modifyUser() {
        // given
        String userId = "test";
        User user = new UserImpl(userId, "tester", "12345", null);
        given(userRepository.existById(userId)).willReturn(true);

        // when
        userService.modifyUser(user);

        // then
        verify(userRepository).modify(user);
    }

    @Test
    @DisplayName("존재하지 않는 아이디로 수정 예외 확인")
    void modifyUser2() {
        // given
        String userId = "test";
        User user = new UserImpl(userId, "tester", "12345", null);

        given(userRepository.existById(userId)).willReturn(false);

        // when , then
        assertThrows(NotFoundUserException.class, () -> userService.modifyUser(user));

    }

    @Test
    @DisplayName("일치할때 결과 테스트")
    void existUser() {
        // given
        String password = "12345";
        String userId = "test";

        User user = new UserImpl(userId, "tester", password, null);

        LoginRequest loginRequest = new LoginRequest();
        ReflectionTestUtils.setField(loginRequest, "userId", userId);
        ReflectionTestUtils.setField(loginRequest, "userPassword", password);

        given(userRepository.existById(userId)).willReturn(true);
        given(userRepository.getUser(loginRequest.getUserId())).willReturn(user);

        //when
        boolean b = userService.existUser(loginRequest);

        // then
        assertThat(b).isTrue();
    }

    @Test
    @DisplayName("일치하지 않을때 결과 테스트")
    void existUser2() {
        // given
        String password = "12345";
        String userId = "test";


        LoginRequest loginRequest = new LoginRequest();
        ReflectionTestUtils.setField(loginRequest, "userId", userId);
        ReflectionTestUtils.setField(loginRequest, "userPassword", password);

        given(userRepository.existById(userId)).willReturn(false);

        //when
        boolean b = userService.existUser(loginRequest);

        // then
        assertThat(b).isFalse();
    }

    @Test
    void getPagedUsers() {
        Page<User> postPage = new PageImpl<>(1, 10, 1, 1, new ArrayList<>());

        given(userRepository.getPagedPosts(1, 10)).willReturn(postPage);

        Page<User> pagedPosts = userService.getPagedUsers(1, 10);

        assertThat(pagedPosts.getPageNumber()).isEqualTo(1);
        assertThat(pagedPosts.getPageSize()).isEqualTo(10);
        assertThat(pagedPosts.getTotalPageCount()).isEqualTo(1);
        assertThat(pagedPosts.getTotalCount()).isEqualTo(1);
    }
}