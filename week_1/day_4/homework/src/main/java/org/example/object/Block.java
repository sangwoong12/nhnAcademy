package org.example.object;

import java.awt.*;
import java.util.function.UnaryOperator;

public class Block extends Item {
    UnaryOperator<org.example.Point> transformer;
    Color color;

    public Block(org.example.Point location, int width, int height, Color color) {
        super(location, width, height,color);
        this.transformer = p -> p;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        org.example.Point[] points = {
                transformer.apply(new org.example.Point(getMinX(), getMinY())),
                transformer.apply(new org.example.Point(getMaxX(), getMinY())),
                transformer.apply(new org.example.Point(getMaxX(), getMaxY())),
                transformer.apply(new org.example.Point(getMinX(), getMaxY()))
        };

        int[] xs = {points[0].getX(), points[1].getX(), points[2].getX(), points[3].getX()};
        int[] ys = {points[0].getY(), points[1].getY(), points[2].getY(), points[3].getY()};

        g.fillPolygon(xs, ys, xs.length);
    }

}
