package org.example.ball;

import java.awt.Color;

import java.util.LinkedList;
import java.util.List;

import org.example.Movable;
import org.example.Point;
import org.example.Vector;

public class MovableBall extends Ball implements Movable {
    Thread thread;
    long interval;
    boolean stop;
    final Vector motion;
    final List<Vector> effectList;

    public MovableBall(Point location, int radius, Color color) {
        super(location, radius, color);
        thread = new Thread(this);
        interval = 100;
        stop = true;
        motion = new Vector();
        effectList = new LinkedList<>();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                next();
                Thread.sleep(interval);
            } catch (InterruptedException ignore) {
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

    public void setInterval(long interval) {
        this.interval = interval;
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
