package org.example;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * 인스턴스 중 화면 출력이 가능한 타입
 */
public interface Drawable {
    void draw(Graphics g);

    void setColor(Color color);

    /**
     * 화면의 조건에 맞게 좌표 변환이 가능한 변환기 설정이 가능하다.
     * @param transformer
     */
    void coordinateTransformer(UnaryOperator<Point> transformer);
}
