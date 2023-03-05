package org.example.block;

import org.example.Bounds;
import org.example.Drawable;
import org.example.Point;

import java.awt.*;
import java.util.function.UnaryOperator;

public class Blocks extends Bounds implements Drawable {
    UnaryOperator<Point> transformer;
    Color color;

    public Blocks(Point location, int width, int height, Color color) {

        super(location,width,height);
        this.transformer = p -> p;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        Point[] points = {
                transformer.apply(new Point(getMinX(), getMinY())),
                transformer.apply(new Point(getMaxX(), getMinY())),
                transformer.apply(new Point(getMaxX(), getMaxY())),
                transformer.apply(new Point(getMinX(), getMaxY()))
        };

        int[] xs = {points[0].getX(), points[1].getX(), points[2].getX(), points[3].getX()};
        int[] ys = {points[0].getY(), points[1].getY(), points[2].getY(), points[3].getY()};

        g.fillPolygon(xs, ys, xs.length);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    //좌표 변환기
    @Override
    public void coordinateTransformer(UnaryOperator<Point> transformer) {
        this.transformer = transformer;
    }
}
