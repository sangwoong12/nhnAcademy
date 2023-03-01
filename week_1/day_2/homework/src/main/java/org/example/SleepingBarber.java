package org.example;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SleepingBarber extends Thread{
    public final Semaphore customers = new Semaphore(0);
    public final Semaphore barber = new Semaphore(0);
    public final Semaphore mutex = new Semaphore(1);
    public int chair = 3;


    class Customer extends Thread{
        int customerNum;
        boolean cutAvailability = true;
        public Customer(int num){
            customerNum = num;
        }
        void cutHair(){
            System.out.println("Customer "+customerNum+": 헤어 커트 완료");
        }
        void visitBarberShop(){
            System.out.println("Customer "+customerNum+": BarberShop 입장");
        }
        void leaveBarberShop(){
            System.out.println("Customer "+customerNum+": 자리가 없어 나감");
        }
        /**
         * 자리가 여부에 따라 커트를 진행하거나 자리에서 떠난다.
         * mutex를 활용하여 자리여부를 확인하는 동안 다른 쓰레드 접근을 막았다.
         */
        @Override
        public void run(){
            while(cutAvailability){
                try{
                    //-1 시켜서 쓰레드 일시 중지
                    //자리여부를 확인하는 동안 손님 쓰레드 wait
                    mutex.acquire();
                    //자리가 있을 경우
                    if(chair > 0){
                        visitBarberShop();
                        chair--;
                        //customers permit 을 증가시켜 barber 가 동작할수 있도록 함
                        customers.release();
                        //착석 이후 permit 양수전환
                        mutex.release();
                        //permit -1 을 했을 때 barber가 0일 경우 cut 중 기다려야함
                        barber.acquire();
                        cutAvailability = false;
                        cutHair();
                    }
                    //자리가 없을 경우
                    else{
                        leaveBarberShop();
                        //떠난 이후 permit 양수전환
                        mutex.release();
                        cutAvailability = false;
                    }
                }catch (InterruptedException e){}
            }
        }
    }
    class Barber extends Thread{
        void cut(){
            System.out.println("Barber : 머리를 자르는 중");
        }
        /**
         * 손님이 들어올 경우 잠에서 깨어나 커트를 한다 (소요시간 1000ms)
         * 손님이 없을경우 쓰레드가 무한대기상태
         */
        @Override
        public void run(){
            while (true){
                try{
                    //손님이 들어와 customers permit을 증가 시킬경우 동작
                    customers.acquire();
                    chair++;
                    //동시 여러명 cut 를 막기위해 필요
                    barber.release();
                    cut();
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){}
                }catch (InterruptedException e){}
            }
        }
    }

    public static void main(String[] args) {
        SleepingBarber sleepingBarber = new SleepingBarber();
        sleepingBarber.start();
    }

    /**
     * 이발사와 랜덤한 시간을 가지고 입장하는 손님 쓰레드를 실행한다.
     */
    @Override
    public void run(){
        //이발사 쓰레드
        Barber barber = new Barber();
        barber.start();
        //랜덤한 시간을 가지고 입장하는 손님 쓰레드
        //시간을 주지 않을경우 동시에 여러명 짜르는 오류를 발견
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            Customer customer = new Customer(i);
            customer.start();
            try{
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){}
        }
    }
}
