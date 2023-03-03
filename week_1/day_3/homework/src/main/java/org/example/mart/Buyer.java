package org.example.mart;

import java.util.concurrent.ThreadLocalRandom;

public class Buyer extends Thread{
    Store store;
    boolean buyCustomer;


    //소비자는 무엇을 얼만큼 살건지 정해서 온다고 가정
    String item;
    int itemNum;
    public Buyer(String name, Store store, String item, int itemNum) {
        super(name);
        this.store = store;
        this.buyCustomer = true;
        this.itemNum = itemNum;
        this.item = item;
    }

    @Override
    public void run() {
        //제약 조건을 주지 않을경우 같은 손님이 계속 들어옴
        while (buyCustomer){
            try{
                store.enter();
                System.out.println(Thread.currentThread().getName()+"손님 입장 :["+item+":"+itemNum+"]를 사러 매장으로 들어갔습니다.");
                store.buy(item,itemNum);
                buyCustomer = false;
                store.exit();
                Thread.sleep(ThreadLocalRandom.current().nextLong(1000,10000));
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
