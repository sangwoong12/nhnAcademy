import com.nhnacademy.notice_board.domain.LoginRequest;
import com.nhnacademy.notice_board.exception.ExistingUserIdException;
import com.nhnacademy.notice_board.exception.NotFoundUserException;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.item.user.UserImpl;
import com.nhnacademy.notice_board.repository.user.JsonUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import com.nhnacademy.notice_board.service.user.ImplUserService;
import com.nhnacademy.notice_board.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class UserServiceTest {
    //jsonUserRepository 를 함께 테스트.

    private static UserService userService;
    private static UserRepository userRepository = new JsonUserRepository();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            userRepository.add(new UserImpl("id" + i, "User-" + i, "12345", null));
        }
        userService = new ImplUserService(userRepository);
    }

    @Test
    void getUserTest() {
        String id = "id0";
        String name = "User-0";
        String password = "12345";
        User user = userService.getUser(id);
        Assertions.assertEquals(user.getId(), id);
        Assertions.assertEquals(user.getName(), name);
        Assertions.assertEquals(user.getPassword(), password);
    }

    @Test
    @DisplayName("존재하지 않는 user 찾을시 오류발생 여부")
    void getUserTest2() {
        Assertions.assertThrows(NotFoundUserException.class, () -> userService.getUser("10000"));
    }

    @Test
    void deleteUserTest() {
        Assertions.assertDoesNotThrow(() -> userService.deleteUser("id0"));
    }

    @Test
    @DisplayName("존재하지 않는 user 삭제시 오류발생 여부")
    void deleteUserTest2() {
        Assertions.assertThrows(NotFoundUserException.class, () -> userService.deleteUser("10000"));
    }

    @Test
    void addUserTest() {
        UserImpl user = new UserImpl("test", "test", "test", null);
        userService.addUser(user);
        User test = userService.getUser("test");
        Assertions.assertEquals(test.getId(), "test");
    }

    @Test
    @DisplayName("이미 존재하는 userId 추가시 오류발생 여부")
    void addUserTest2() {
        UserImpl user = new UserImpl("id1", "test", "test", null);
        Assertions.assertThrows(ExistingUserIdException.class, () -> userService.addUser(user));
    }

    @Test
    void modifyUserTest() {
        UserImpl user = new UserImpl("test", "test", "test", null);
        Assertions.assertThrows(NotFoundUserException.class, () -> userService.modifyUser(user));
    }

    @Test
    @DisplayName("id가 겹칠경우")
    void existUserTest() {
        LoginRequest loginRequest = new LoginRequest();
        ReflectionTestUtils.setField(loginRequest, "userId", "id0");
        ReflectionTestUtils.setField(loginRequest, "userPassword", "test");
        Assertions.assertFalse(userService.existUser(loginRequest));
    }

    @Test
    @DisplayName("id가 안겹칠 경우")
    void existUserTest2() {
        LoginRequest loginRequest = new LoginRequest();
        ReflectionTestUtils.setField(loginRequest, "userId", "existUser");
        ReflectionTestUtils.setField(loginRequest, "userPassword", "test");
        Assertions.assertFalse(userService.existUser(loginRequest));
    }
}
