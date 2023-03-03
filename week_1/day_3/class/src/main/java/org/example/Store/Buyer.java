package org.example.Store;

/**
 * 소비자는 매장에 입장 후 물건을 구매할 수 있다.
 * 매장에는 입장 인원 제한이 있으므로, 인원 초과시 기다린다.
 * 매장에 입장하면 물건을 구매하고, 퇴장한다.
 * 1~10초 간격으로 구매한다.
 */
public class Buyer extends Thread{
    Store store;
    public Buyer(String name, Store store){
        super(name);
        this.store = store;
    }

    @Override
    public void run() {

    }
}
