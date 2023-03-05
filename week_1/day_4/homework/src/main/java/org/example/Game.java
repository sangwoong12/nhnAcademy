package org.example;

import org.example.object.Block;
import org.example.world.BallWorld;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    final BallWorld world;

    public Game(int width, int height){
        super();

        setSize(width,height);
        setLayout(null);

        world = new BallWorld(getWidth() - 100,getHeight() - 100);
        world.setLocation(50,60);

        add(world);
        world.setInterval(20);


    }
    public void run(long seconds){

        for(int i = 0;  i < 10; i++){
            Block block = new Block(new Point(world.getWidth() - 52*i -200, 300), 50, 20, Color.BLACK);
            Block block2 = new Block(new Point(world.getWidth() - 52*i -200, 322), 50, 20, Color.BLACK);
            Block block3 = new Block(new Point(world.getWidth() - 52*i -200, 344), 50, 20, Color.BLACK);
            world.add(block3);
            world.add(block2);
            world.add(block);
        }
        world.run(seconds);
    }
    public void close(){
        Thread.currentThread().interrupt();
    }
}
