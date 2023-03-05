package org.example.world;

import org.example.Bounds;
import org.example.Movable;

public class MovableWorld extends World {
    long interval;

    public MovableWorld(int width, int height) {
        super(width, height);
        interval = 10;
    }
    public long getInterval() {
        return interval;
    }
    public void setInterval(long interval) {
        this.interval = interval;
        synchronized (boundsList) {
            for (Bounds bounds : boundsList) {
                if (bounds instanceof Movable) {//움직이는 상태이면
                    ((Movable) bounds).setInterval(interval);
                }
            }
        }
    }
    @Override
    public void add(Bounds bounds) {
        super.add(bounds);
        if (bounds instanceof Movable) {
            ((Movable) bounds).start();
        }
    }
    public void next() {
        synchronized (boundsList) {
            for (Bounds bounds : boundsList) {
                if (bounds instanceof Movable) {
                    ((Movable) bounds).next();
                }
            }
        }
    }
    public void run(long seconds) {
        long startTime = System.currentTimeMillis();
        while (!Thread.interrupted() && (System.currentTimeMillis() < startTime + seconds * 1000)) {
            try {
                repaint();
                revalidate();
                setInterval(10);
                Thread.sleep(interval);

            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
