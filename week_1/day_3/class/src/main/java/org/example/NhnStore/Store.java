package org.example.NhnStore;

import java.util.concurrent.Semaphore;

public class Store {
    Semaphore numOfCustomer;
    int boxCount;
    public Store() {
        this.numOfCustomer = new Semaphore(5);
    }
    public void enter() throws InterruptedException{
        try{
            numOfCustomer.acquire();
        }catch (InterruptedException e){
            throw e;
        }
    }
    public void exit(){
        numOfCustomer.release();
        System.out.println(Thread.currentThread().getName()+" 퇴장");
    }
    public synchronized void buy(){
        while (boxCount == 0){
            try{
                System.out.println(Thread.currentThread().getName()+" 구매 대기");
                wait();
                Thread.sleep(100);
            }catch (InterruptedException e){}
        }
        --boxCount;
        System.out.println("구매 완료, 제고 : "+boxCount);
        notify();
    }
    public synchronized void sell(){
        while (boxCount >= 10){
            try{
                System.out.println("납품 대기중입니다.");
                wait();
                Thread.sleep(100);
            }catch (InterruptedException e){}
        }
        ++boxCount;
        System.out.println("낙품 완료. 제고 : "+boxCount);
        notify();
    }
}
