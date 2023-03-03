package Synchronized;

public class TestCircularWaitDeadlock {
    public static void main(String[] args) {
        Object resource1 = new Object();
        Object resource2 = new Object();

        Thread task1 = new Thread(() -> {
            while(!Thread.interrupted()){
                synchronized (resource1){
                    System.out.println("Task1 :resource1 진입");
                    synchronized (resource2){
                        System.out.println("Task1 : resource2 진입 = " + resource1);
                    }
                }
            }
        });

        Thread task2 = new Thread(() -> {
            while(!Thread.interrupted()) {
                synchronized (resource2) {
                    System.out.println("Task2 : resource2 진입");
                    synchronized (resource1) {
                        System.out.println("task2 : resource1 진입 = " + resource2);

                    }
                }
            }
        });
        task1.start();
        task2.start();
    }
}
