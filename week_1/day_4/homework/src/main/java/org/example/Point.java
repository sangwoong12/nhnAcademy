package org.example;

public class Point implements Cloneable{//인스턴스 복사

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(Point other){
        this(other.getX(),other.getY());
    }

    /**
     * x,y 값만큼 이동
     *
     * @param dx
     * @param dy
     * @return
     */
    public Point move(int dx, int dy){
        x += dx;
        y += dy;
        return this;
    }

    /**
     * 변위 만큼 이동
     *
     * @param displacement
     * @return
     */
    public Point move(Point displacement){
        x += displacement.getX();
        y += displacement.getY();
        return this;
    }

    /**
     * 주어진 좌표로 이동
     *
     * @param x
     * @param y
     * @return
     */
    public Point moveTo(int x, int y){
        this.x = x;
        this.y = y;

        return this;
    }

    /**
     * 주어진 변위로 이동
     *
     * @param other
     * @return
     */
    public Point moveTo(Point other) {
        x = other.getX();
        y = other.getY();

        return this;
    }


    @Override
    protected Point clone() {
        try{
            return (Point) super.clone();
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
