package org.example.world;


import org.example.Point;
import org.example.object.Ball;

import java.awt.*;

public class BallWorld extends BoundedWorld {
    Ball ball;
    public BallWorld(int width, int height){
        super(width,height);
        ball = new Ball(new Point( 500, 500), 20, Color.BLUE);
        //ball.setMotion(new Vector(20,90));
        add(ball);
    }

}
