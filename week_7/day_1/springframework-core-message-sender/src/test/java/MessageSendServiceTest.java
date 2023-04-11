import com.nhnacademy.edu.springframework.messagesender.User;
import com.nhnacademy.edu.springframework.sender.MessageSendService;
import com.nhnacademy.edu.springframework.sender.sender.MessageSender;
import com.nhnacademy.edu.springframework.sender.sender.SmsMessageSender;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageSendServiceTest {
    private MessageSendService messageSendService;// = new MessageSendService();
//    @Test
//    public void test(){
//        ReflectionTestUtils.setField(messageSendService,"messageSender",new SmsMessageSender());
//        ReflectionTestUtils.setField(messageSendService,"name","Sangwoong"
//        );
//        messageSendService.doSendMessage();
//    }
    @Test
    public void mockTest(){
        MessageSender messageSender = mock(SmsMessageSender.class);
        messageSendService = new MessageSendService(messageSender);
        messageSendService.doSendMessage();

    }
}
