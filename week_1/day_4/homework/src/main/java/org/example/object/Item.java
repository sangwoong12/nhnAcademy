package org.example.object;

import org.example.Bounds;
import org.example.Drawable;
import org.example.Point;

import java.awt.*;
import java.util.function.UnaryOperator;

public class Item extends Bounds implements Drawable {
    UnaryOperator<Point> transformer;
    Color color;

    /**
     * 생성자
     *
     * @param location
     * @param width
     * @param height
     * @param color
     */
    public Item(org.example.Point location, int width,int height, Color color) {
        super(location, width, height);
        this.transformer = p -> p;
        this.color = color;
    }

    public UnaryOperator<Point> getTransformer() {
        return transformer;
    }

    /**
     * 볼의 색 설정.
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 반지름을 얻어온다.
     *
     * @return
     */
    public int getRadius(){
        return getWidth() / 2;
    }

    /**
     * 좌표계를 변환할 수 있는 변환기를 설정한다.
     *
     * @param transformer
     */
    public void coordinateTransformer(UnaryOperator<org.example.Point> transformer) {
        this.transformer = transformer;
    }

    /**
     * 볼 그리기.
     *
     * @param g 그래픽 컨텍스트
     */
    @Override
    public void draw(Graphics g) {

    }
}
