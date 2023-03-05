package org.example;

import java.awt.*;

public class Bounds {
    Point location;
    int width;
    int height;

    public Bounds(Point location, int width, int height) {
        this.location = location;
        this.width = width;
        this.height = height;
    }

    public Point getLocation() {
        return location;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getMinX(){
        return location.getX() - width / 2;
    }
    public int getMinY(){
        return location.getY() - height / 2;

    }
    public int getMaxX(){
        return location.getX() + width / 2;
    }
    public int getMaxY(){
        return location.getY() + height / 2;
    }
    //충돌 여부
    public boolean isCollision(Bounds other) {
        return  getRectangle().intersects(other.getRectangle());
    }
    //교차로
    protected Rectangle getIntersection(Bounds other) {
        return  getRectangle().intersection(other.getRectangle());
    }

    protected Rectangle getRectangle() {
        return  new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
    }
}
