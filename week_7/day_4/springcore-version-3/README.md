- 요구 사항
  - 만들어야 하는 기능은 다음 프로젝트 마무리 실습을 참고
  - 프로젝트 마무리 실습
  - 애플리케이션을 실행하면 메신저에 웹 훅을 전달한다.
- 다음 예제 코드를 수정하여 DoorayMessageSender 와 DoorayHookSender 모두 스프링 빈으로 선언해야 한다.
```java
public class DoorayMessageSender implements MessageSender
    @Override
    public boolean sendMessage(User user, String message) {
        new DoorayHookSender(new RestTemplate(), "${hookUrl}")
                .send(DoorayHook.builder()
                        .botName("${작성자 이름}")
                        .text("${동료들에게 하고싶은말}")
                        .build());
        return true;
    }
}
```
- DoorayMessageSender 테스트 코드를 작성한다.
  - 이때 DoorayHookSender 는 목(Mock)으로 작성한다.
- DoorayMessageSender 의 sendMessage() 메서드의 실행 속도를 측정한다.
  - 커스텀 애너테이션을 만든다.
  - 커스텀 애너테이션 + AOP 의 포인트컷 표현식을 이용하여 DoorayMessageSender 의 sendMessage() 메서드만 정확하게 지정한다.
  - 로그 형식은 다음과 같다.
  - [class name].[method name] [속도]ms


- 제출 1
  - ClassPathXmlApplicationContext + beans.xml 을 사용하여 프로그램을 작성한다. 단 스프링 빈 선언은 beans.xml 에 한다.
- 제출 2
  - AnnotationConfigApplicationContext + JavaConfig 를 사용하여 프로그램을 작성한다. 단 스프링 빈 선언은 @Bean 을 사용한다.
- 제출 3
  - AnnotationConfigApplicationContext + JavaConfig 를 사용하여 프로그램을 작성한다. 단 스프링 빈 선언은 Stereotype annotations 을 사용한다.
