package org.example;

public class Test {
    public static void main(String[] args) {
        AThread task1 = new AThread();
//        Thread task2 = new Thread(new BThread());
//        task2.start();
        Runnable thread1 = () -> {
            System.out.println("BThread");
        };
        Thread task2 = new Thread(thread1);
        task1.start();
        task2.start();
    }

}
class AThread extends Thread{
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(500);
                System.out.println("AThread");
            } catch (InterruptedException e) {
            }
        }
    }
}
class BThread implements Runnable{
    @Override
    public void run(){
        try {
            Thread.sleep(500);
            System.out.println("BThread");
        } catch (InterruptedException e) {
        }
    }
}
