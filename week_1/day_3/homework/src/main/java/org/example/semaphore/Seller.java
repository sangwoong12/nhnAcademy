package org.example.semaphore;

public class Seller extends Thread{
    Store store;
    String item;
    int itemNum;

    //판매자가 무엇을 얼만큼 납품할지를 정함
    public Seller(Store store, String item, int itemNum){
        this.store = store;
        this.item = item;
        this.itemNum = itemNum;
    }

    /**
     * 마트에 생산자가 물품과 물품의 갯수를 등록한다.
     */
    @Override
    public void run() {
        while(!Thread.interrupted()) {
            store.sell(item, itemNum);
        }
    }
}
