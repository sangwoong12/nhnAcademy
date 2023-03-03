package org.example.NhnStore;

import java.util.concurrent.ThreadLocalRandom;

public class Buyer extends Thread{
    Store store;
    boolean buyCustomer;

    public Buyer(String name, Store store) {
        super(name);
        this.store = store;
        this.buyCustomer = true;
    }

    @Override
    public void run() {
        while (buyCustomer){
            try{
                store.enter();
                System.out.println(Thread.currentThread().getName()+"손님 입장");
                store.buy();
                buyCustomer = false;
                store.exit();
                Thread.sleep(ThreadLocalRandom.current().nextLong(1000,10000));
            }catch (InterruptedException e){}
        }
    }
}
