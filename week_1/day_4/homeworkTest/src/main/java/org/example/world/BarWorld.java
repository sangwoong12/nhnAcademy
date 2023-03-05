package org.example.world;

import org.example.*;
import org.example.Point;
import org.example.block.unbreakable.Bar;

import java.awt.event.KeyEvent;

public class BarWorld extends BlockWorld{
    Bar bar;
    int MaxWidth;
    public BarWorld(int width, int height) {
        super(width, height);
        this.MaxWidth = width;
    }

    public void barUse(KeyEvent e){
         if(e.getKeyCode() == KeyEvent.VK_LEFT){
            bar.setMotion(new Vector(3,180));
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT){
             bar.setMotion(new Vector(3,0));
         }
    }

    @Override
    public void startGame() {
        super.startGame();
        bar = new Bar(new Point(MaxWidth/2,100));
        add(bar);
    }
}
