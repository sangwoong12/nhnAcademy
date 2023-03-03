package Synchronized;

import org.example.Synchronized.CountAgent;
import org.example.Synchronized.Counter;

import java.util.Locale;

public class TestCounter {
    public static void main(String[] args) {
        Counter counter = new Counter();
        int numberOfIterations = 1000000;

        CountAgent agent1 = new CountAgent(counter, numberOfIterations, true);
        CountAgent agent2 = new CountAgent(counter, numberOfIterations, false);

        long startTime = System.currentTimeMillis();
        agent1.start();
        agent2.start();

        try {
            agent1.join();
            agent2.join();
        } catch(InterruptedException ignore) {
        }

        System.out.println("Count : " + counter.getCount());
        System.out.println("Another : " + counter.getAnotherCount());

        long time = System.currentTimeMillis() - startTime;
        System.out.println("Time :"+ time);
    }
}