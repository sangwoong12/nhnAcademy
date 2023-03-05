package org.example.movable;

import org.example.Point;
import org.example.Vector;
import org.example.object.Item;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class unMovableBlock extends Item implements Movable {
    Thread thread;
    long interval;
    boolean stop;
    final Vector motion;
    final List<Vector> effectList;

    public unMovableBlock(Point location, int width, int height, Color color) {
        super(location, width,height, color);
        thread = new Thread(this);
        interval = 100;
        stop = true;
        motion = new Vector();
        effectList = new LinkedList<>();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try{
                next();
                Thread.sleep(interval);
            } catch (InterruptedException ignore){
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start() {
        this.thread.start();
    }

    public void stop() {
        this.thread.interrupt();
    }

    public void setMotion(Vector motion) {
        this.motion.set(motion);
    }

    public Vector getMotion() {
        return motion;
    }
    public void setInterval(long interval){
        this.interval =interval;
    }

    public void addEffect(Vector effect) {
        effectList.add(effect);
    }

    public void next() {
        for (Vector effect : effectList) {
            getMotion().add(effect);
        }

        getLocation().move(motion.getDisplacement());
    }


}
