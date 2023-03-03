package Synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestHoldAndWaitDeadlock {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread task1 = new Thread(()-> {
            while(!Thread.interrupted()) {
                try{
                    System.out.println("Task 1: lock ");
                    lock.lock();
                    Thread.sleep(1000);
                    lock.unlock();
                    System.out.println("Task 1: unlock");
                }catch (InterruptedException e){}
            }
        });

        Thread task2 = new Thread(()-> {
                try{
                    System.out.println("Task 2: lock");
                    lock.lock();
                    Thread.sleep(10000);
                    lock.unlock();
                    System.out.println("Task 2: unlock");
                }catch (InterruptedException e){
                    System.out.println("Task 2: interrupt");
                    Thread.currentThread().interrupt();
                }
            }
        );
        task1.setName("task1");
        task2.setName("task2");
        task1.start();
        task2.start();

        try {
            for(int i = 0 ; i < 10 ; i++) {
                //System.out.println("Task1 : " + task1.getState() + ", Task2 : " + task2.getState());
                if (i == 2) {
                    task2.interrupt();
                }
                Thread.sleep(1000);
            }
            task1.join();
            task2.join();
        } catch(InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }

        System.out.println("테스트 종료");
    }
}
