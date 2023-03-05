package org.example.world;

import org.example.*;
import org.example.ball.BoundedBall;
import org.example.ball.MovableBall;
import org.example.block.breakable.BreakableBlock;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class BallWorld extends BoundedWorld {

    transient List<Bounds> targets;
    transient Bounds target;
    int score;
    int boundsCount;
    transient BoundedBall ball;
    transient List<Vector> effectList;
    static final int BALL_SPEED = 15;
    static final int BALL_X = 500;
    static final int BALL_Y = 100;
    static final int BALL_RADIUS = 5;
    public BallWorld(int width, int height) {
        super(width, height);
        targets = new CopyOnWriteArrayList<>();
        effectList = new LinkedList<>();
        score = 0;
    }
    public void setTarget(Bounds target) {
        this.targets.add(target);
        add(target);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score+= score;
    }
    public int getNumOfBlocks(){
        return targets.size();
    }

    public int getBoundsCount() {
        return boundsCount;
    }
    public void DashboardEvent(Bounds bounds){

    }
    @Override
    public void add(Bounds bounds) {
        super.add(bounds);
        DashboardEvent(bounds);
        if (bounds instanceof Bounded) {
            ((Bounded) bounds).addCollisionEventListener(event -> {
                if (!targets.isEmpty()) {
                    for (Bounds target : targets) {
                        if (event.getDestination() == target && target instanceof BreakableBlock) {
                                ((BreakableBlock) target).decreaseBlockHp();
                                if (((BreakableBlock) target).breakable()) {
                                    this.target = target;
                                    ((MovableBall) bounds).next();
                                    boundsList.remove(target);
                                    super.destroyBounds(target);
                                    setScore(((BreakableBlock) target).getScore());
                                    System.out.println(getScore());
                                    ((MovableBall) bounds).setMotion( new Vector(BALL_SPEED, ((MovableBall) bounds).getMotion().getAngle() + random.nextInt(30)));
                                    break;
                                }
                        }
//                        else if (event.getDestination() == ball) {
//                            if (target instanceof Drawable) {
//                                ((Drawable) ball).setColor(Color.RED);
//                                ((Movable) bounds).stop();
//                            }
//                        }
                    }
//                    else if (hazardList.contains(event.getDestination())) {
//                        ((Movable) bounds).stop();
//                    }
                }
            });
        }

        for (Vector effect : effectList) {
            if (bounds instanceof Movable) {
                ((Movable) bounds).addEffect(effect);

            }
        }
    }
    public void startGame(){
        if (ball != null) {
            super.destroyBounds(ball);
            boundsList.remove(ball);
            ball.stop();
        }

        ball = new BoundedBall(new Point(BALL_X, BALL_Y), BALL_RADIUS, Color.BLUE);
        ball.setMotion(new Vector(BALL_SPEED, 65));
        add(ball);
    }
    public void resetGame(){
        synchronized (boundsList) {
            boundsList.removeIf(Movable.class::isInstance);
        }
    }
}
