package org.example.NhnStore;

import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread{
    Store store;

    public Seller(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try{
                store.sell();
                Thread.sleep(ThreadLocalRandom.current().nextLong(1000,2000));
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
