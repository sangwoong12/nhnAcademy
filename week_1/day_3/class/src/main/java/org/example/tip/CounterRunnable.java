package org.example.tip;

import java.util.concurrent.locks.ReentrantLock;

public class CounterRunnable implements Runnable{
    String name;
    int count;
    public CounterRunnable(String name) {
        this.name = name;
        this.count = 0;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            count++;
            System.out.println(name+" : "+count);
        }
    }
}
