package org.example.tip;

public class CounterThread extends Thread{
    String name;
    int count;

    public CounterThread(String name){
        this.name = name;
        this.count = 0;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                interrupt();
            }
            count++;
            System.out.println(name+" : "+count);
        }
    }
}
