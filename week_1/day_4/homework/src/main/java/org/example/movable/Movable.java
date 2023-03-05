package org.example.movable;

import org.example.Vector;

/**
 * 이동 공간에서 이동이 가능한 물체가 가지는 타입
 */
public interface Movable extends Runnable{

    /**
     * 움직일 수 있음을 설정할 수 있다.
     */
    void start();
    void stop();
    void setMotion(Vector motion);
    Vector getMotion();
    void setInterval(long interval);
    void addEffect(Vector effect);
    void next();
}
