package org.example.Store;

import java.util.concurrent.Semaphore;

public class Store {
    Semaphore numOfItem ;
    Semaphore numOfCustomer;
    //    Semaphore buyItem = new Semaphore(0);
    Semaphore seller;
    public Store() {
        this.numOfItem = new Semaphore(10);
        this.numOfCustomer = new Semaphore(5);
        this.seller = new Semaphore(0);
    }
    public void enter(){
        try{
            numOfCustomer.acquire();
        }catch (InterruptedException e){}
    }
    public void exit(){
        numOfCustomer.release();
    }
    public synchronized void buy(){
        numOfItem.release();
    }
    public synchronized void sell(){
        try {
            seller.acquire();
            numOfItem.acquire();
            System.out.println("물건이 들어왔습니다.");
            seller.release();
        }catch (InterruptedException e){}
    }
}
