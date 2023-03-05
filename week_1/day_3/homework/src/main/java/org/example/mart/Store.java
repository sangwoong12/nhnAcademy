package org.example.mart;

import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Javadoc.
 *
 * @author 박상웅 javadoc.
 */
public class Store {
    Semaphore numOfCustomer;
    //품목 매장을 hashMap으로 구현
    HashMap<String, Integer> itemStores = new HashMap<>();
    //품목별 semaphore을 hashMap으로 구현
    HashMap<String, Semaphore> accessOfItem = new HashMap<>();
    //N개의 품목 매장, 품목별 semaphore 초기 등록

    /**
     * 요구사항 마트에서는 N개의 품목 매장이 있다.
     * 품목 매장과 품목별 세마포어는 hashmap으로 구현하였다.
     *
     * @param storeNames : N개의 품목
     */
    public Store(String... storeNames) {
        for (String storeName : storeNames) {
            itemStores.put(storeName, 0);
            accessOfItem.put(storeName, new Semaphore(1));
        }
        this.numOfCustomer = new Semaphore(5);
    }

    /**
     * 손님이 입장하는 메서드.
     */
    public void enter() {
        try {
            numOfCustomer.acquire();
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
    }

    public void exit() {
        numOfCustomer.release();
        System.out.println(Thread.currentThread().getName() + " 퇴장");
    }

    /**
     * 요구사항에 따라 생산자는 N개의 품목을 납품할 수 있고, 소비자는 품목별 매장을 이용할 수 있고 품목이 다르다면 동시구매 가능하다.
     * 소비자는 무엇을 얼만큼 살 것을 정해서 오는데 살려는 갯수보다 매장의 품목갯수가 적으면 대기하고, 적지 않다면 구매를 진행한다.
     * 개별적으로 세마포어로 통제하기 때문에 품목이 다르다면 동시구매 가능하다.
     *
     * @param item : 소비자가 살려는 품목
     * @param itemNum : 소비자가 살려는 품목의 갯수
     */
    public void buy(String item, int itemNum) {
        try {
            accessOfItem.get(item).acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //살려는 갯수보다 있는 물품 갯수가 적으면 반복 , 구매 대기 알림은 한번만
        System.out.println(Thread.currentThread().getName()
                + " [" + item + ":" + itemNum + "] 구매 대기");
        while (itemStores.get(item) < itemNum) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
        //품목 매장에 제고 수정
        itemStores.put(item, itemStores.get(item) - itemNum);
        System.out.println(Thread.currentThread().getName() + "손님 구매 완료, 제고 : ["
                + item + ":" + itemStores.get(item) + "]");
        accessOfItem.get(item).release();
    }

    /**
     * 요구사항에 따라 생산자는 N개의 품목을 납품할 수 있고, 마트에서는 생산자가 품목을 납품하기 전까지는 어떤 품목인지 알 수 없다.
     * 품목 매장의 품목 갯수와 등록할려는 갯수의 합이 10개 가 넘는다면 다른 생산자가 들어오고, 적다면 납품을 진행한다.
     * 인자로 값을 받아 오기전까지 품목이 무엇인지 알수가 없다.
     *
     * @param item : 생산자가 납품할 품목
     * @param num : 생산자가 납품할 품목의 갯수
     */
    public synchronized void sell(String item, Integer num) {
        while (itemStores.get(item) + num > 10) {
            System.out.println(item + "가" + (itemStores.get(item) + num) + "로 초과하여 다른 생산자가 들어옵니다.");
            try {
                wait(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        //품목 매장에 제고 수정
        itemStores.put(item, itemStores.get(item) + num);
        System.out.println("납품 완료. 제고 : [" + item + ":" + itemStores.get(item) + "]");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(1000, 5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
