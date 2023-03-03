package org.example.tip;

public class Pipe {
    int data;
    boolean empty;

    public Pipe() {
        this.data = 0;
        this.empty = true;
    }

    public synchronized int receive() throws InterruptedException{
        while (empty){
            try {
                wait();
                Thread.sleep(1000);
            }catch (InterruptedException e){
                throw e;
            }
        }
        notify();
        empty = true;
        return data;
    }
    public synchronized void send(int data) throws InterruptedException{
        while (!empty){
            try {
                wait();
                Thread.sleep(1000);
            }catch (InterruptedException e){
                throw e;
            }
        }
        notify();
        this.data = data;
        this.empty = false;
    }

}
