package org.example.tip;

import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable{
    Pipe pipe;

    public Sender(Pipe pipe) {
        this.pipe = pipe;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try{
                pipe.send(ThreadLocalRandom.current().nextInt());
                Thread.sleep(ThreadLocalRandom.current().nextLong(1000,5000));
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
