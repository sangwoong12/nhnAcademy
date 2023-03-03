package org.example.tip;

public class Counter extends Thread{
    String name;
    int count;

    public Counter(){
        this.name = "Counter";
        this.count = 0;
    }
    public Counter(String name){
        this.name = name;
        this.count = 0;
    }
    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){}
            count++;
            System.out.println(name+" : "+count);
        }
    }
}
