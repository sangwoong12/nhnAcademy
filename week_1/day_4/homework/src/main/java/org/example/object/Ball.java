package org.example.object;

import org.example.Point;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * 게임을 구성하는 둥근 물체.
 */
public class Ball extends Item{
    UnaryOperator<Point> transformer;
    Color color;

    /**
     * 생성자.
     *
     * @param location 볼 위치
     * @param radius   반지름
     * @param color    색깔
     */
    public Ball(Point location, int radius, Color color) {
        super(location, 2 * radius, 2 * radius,color);
        this.transformer = p -> p;
        this.color = color;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        Point p1 = transformer.apply(getLocation());

        g.fillOval(p1.getX() - getRadius(), p1.getY() - getRadius(), getWidth(), getHeight());
    }
}
