package CounterThread;

import org.example.tip.CounterRunnable;

public class TestCounter{
    public static void main(String[] args) {
        //단순히 run을 호출할경우 runable에 있는 메서드를 호출한것이 때문에 run 이 끝나지 않는 이상 다음 run 이 동작하지 않는다.
        CounterRunnable counterRunnable = new CounterRunnable("CounterRunnable");
        Thread thread = new Thread(counterRunnable, "Counter");

        thread.start();


    }
}
