package org.example.world;

import org.example.Bounds;
import org.example.movable.Movable;
import org.example.World;

public class MovableWorld extends World {
    long interval;

    public MovableWorld(int width, int height) {
        super(width, height);
        interval = 100;
    }
    public long getInterval(){
        return interval;
    }
    public void setInterval(long interval){
        this.interval = interval;
        synchronized (getBoundsList()){
            for(Bounds bounds : getBoundsList()){
                if(bounds instanceof Movable){//움직이는 상태이면
                    ((Movable) bounds).setInterval(interval);//이유 모를 링크드 리스트라서 쓸대없는 과정이 길다.
                }
            }
        }
    }
    public void add(Bounds bounds){
        super.add(bounds);
        if(bounds instanceof Movable){
            ((Movable) bounds).start();
        }
    }
    public void next(){
        synchronized (getBoundsList()){
            for(Bounds bounds : getBoundsList()){
                if(bounds instanceof Movable){
                    ((Movable) bounds).next();
                }
            }
        }
    }
    public void run(long seconds){
        long startTime = System.currentTimeMillis();

        while (!Thread.interrupted() && (System.currentTimeMillis() < startTime + seconds * 1000)){
            try{
                repaint();
                setInterval(20);
                Thread.sleep(interval);
            } catch (InterruptedException ignore){
                Thread.currentThread().interrupt();
            }
        }
    }
}
