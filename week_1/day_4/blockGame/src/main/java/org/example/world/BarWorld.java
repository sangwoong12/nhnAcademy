package org.example.world;

import org.example.*;
import org.example.Point;
import org.example.block.unbreakable.Bar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 하단에서 움직이는 바를 생성 및 KeyListener 에 관한 정보를 담고 있다.
 */
public class BarWorld extends BlockWorld{
    static final int BAR_MAGNITUDE = 5;
    Bar bar;
    int MaxWidth;
    public BarWorld(int width, int height) {
        super(width, height);
        this.MaxWidth = width;

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //키보드 입력에 대한 바의 움직임
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    bar.setMotion(new Vector(BAR_MAGNITUDE, 180));
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    bar.setMotion(new Vector(BAR_MAGNITUDE, 0));
                }
            }
        });
    }

    /**
     * 게임 시작시 바를 생성
     */
    @Override
    public void startGame() {
        super.startGame();
        bar = new Bar(new Point(MaxWidth / 2, 70));
        add(bar);
    }

}
