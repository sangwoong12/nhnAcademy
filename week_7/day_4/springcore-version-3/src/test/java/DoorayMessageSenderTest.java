import com.nhnacademy.edu.springframework.DoorayHookSender;
import com.nhnacademy.edu.springframework.item.User;
import com.nhnacademy.edu.springframework.message_sender.DoorayMessageSender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@SuppressWarnings("-Xlint:unchecked")
public class DoorayMessageSenderTest {
    @Test
    void test() {
        User user = new User("test");
        String message = "test";

        DoorayHookSender doorayHookSender = mock(DoorayHookSender.class);
        DoorayMessageSender doorayMessageSender = new DoorayMessageSender(doorayHookSender);

        assertTrue(doorayMessageSender.sendMessage(user, message));
    }

    @Test
    void nullUserNameTest() {
        User user = new User("");
        String message = "message";

        DoorayHookSender doorayHookSender = mock(DoorayHookSender.class);
        DoorayMessageSender doorayMessageSender = new DoorayMessageSender(doorayHookSender);
        assertEquals(false, doorayMessageSender.sendMessage(user, message));
    }

    @Test
    void nullMessageTest() {
        User user = new User("user");
        String message = "";

        DoorayHookSender doorayHookSender = mock(DoorayHookSender.class);
        DoorayMessageSender doorayMessageSender = new DoorayMessageSender(doorayHookSender);
        assertEquals(false, doorayMessageSender.sendMessage(user, message));
    }
}
