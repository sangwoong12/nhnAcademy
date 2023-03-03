package org.example.tip;


public class Receiver implements Runnable{
    Pipe pipe;
    /*
    Thread thread;
    this.thread = new Thread(this)
    위와같이 구현하여 메서드를 구현하여 쓰레드처럼 사용하여도 된다.
    ex) void start(){ this.thread.start}
    */
    public Receiver(Pipe pipe) {
        this.pipe = pipe;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try{

                System.out.println("Received Data : "+pipe.receive());
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
