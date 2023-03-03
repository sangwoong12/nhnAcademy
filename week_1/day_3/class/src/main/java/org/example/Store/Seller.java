package org.example.Store;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 생산자는 매장에 물건이 비워지 않도록 채워둔다.
 * 물건은 1~10초 간격으로 채운다.
 */
public class Seller extends Thread{
    Store store;

    public Seller(Store store){
        this.store = store;
    }

    @Override
    public void run() {

    }

}
