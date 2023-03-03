package Synchronized;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class TestClothingStore {
    public static void main(String[] args) {
        Semaphore dressRooms = new Semaphore(5);
        Thread[] customers = new Thread[10];
        for(int i = 0; i < customers.length; i++){
            int finalI = i;
            customers[i] = new Thread(() -> {
                try {
                    System.out.println("대기 :"+finalI+"손님");
                    dressRooms.acquire();
                    System.out.println("입장 :"+finalI+"손님"+dressRooms.drainPermits());
                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000,5000));
                    dressRooms.release();
                    System.out.println("퇴장 :"+finalI+"손님");
                }catch (InterruptedException e){}
            });
            customers[i].start();
        }
    }
}
