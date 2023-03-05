package org.example.world;

import org.example.*;
import org.example.ball.BoundedBall;
import org.example.ball.MovableBall;
import org.example.block.Blocks;
import org.example.block.breakable.BreakableBlock;

import javax.swing.*;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 볼 생성, 게임 시작 버튼 및 상황판에 관한 액션 담 .
 */
public class BallWorld extends BoundedWorld {
    static final int BALL_SPEED = 5;
    static final int BALL_X = 500;
    static final int BALL_Y = 110;
    static final int BALL_RADIUS = 8;
    transient List<Bounds> targets;
    int score;
    BoundedBall ball;
    List<Vector> effectList;
    List<Bounds> hazardList;
    JLabel scoreLabel;
    JLabel blockOfCountLabel;
    public BallWorld(int width, int height) {
        super(width, height);
        targets = new CopyOnWriteArrayList<>();
        effectList = new LinkedList<>();
        hazardList = new LinkedList<>();
        score = 0;
        makeBoard();
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

    public void addHazard(Bounds hazard) {
        hazardList.add(hazard);
        add(hazard);
    }

    /**
     * 볼과 접촉시 일어나는 모든 행위를 담고 있다.
     * 블럭을 파괴하거나, 볼이 바닥에 접촉하여 게임이 종료될 때 사용된다.
     *
     * @param bounds
     */
    @Override
    public void add(Bounds bounds) {
        super.add(bounds);
        if (bounds instanceof Bounded) {
            ((Bounded) bounds).addCollisionEventListener(event -> {
                if (!targets.isEmpty()) {
                    for (Bounds target : targets) {
                        if (event.getDestination() == target && target instanceof BreakableBlock) {
                            ((BreakableBlock) target).decreaseBlockHp();
                            //블럭이 부셔질 경우 리스트에서 제외한다.
                            if (((BreakableBlock) target).breakable()) {
                                ((MovableBall) bounds).next();
                                boundsList.remove(target);
                                targets.remove(target);
                                super.destroyBounds(target);
                                setScore(((BreakableBlock) target).getScore());
                                //보드 업데이트
                                boardUpdate();
                                //방향 랜덤값 추가
                                ((MovableBall) bounds).setMotion(new Vector(BALL_SPEED, ((MovableBall) bounds).getMotion().getAngle() + random.nextInt(20)));
                                break;
                            }
                        }
                        //경계 구역 접촉시 게임 리셋
                        else if (hazardList.contains(event.getDestination())) {
                            resetGame();
                        }
                    }
                }
            });
        }
        for (Vector effect : effectList) {
            if (bounds instanceof Movable) {
                ((Movable) bounds).addEffect(effect);

            }
        }
    }

    /**
     *
     * 시작 버튼 누를 경우 게임 시작한다.
     */
    public void startGame() {
        boardUpdate();
        if (ball != null) {
            super.destroyBounds(ball);
            boundsList.remove(ball);
            ball.stop();
        }

        ball = new BoundedBall(new Point(BALL_X, BALL_Y), BALL_RADIUS, Color.BLUE);
        ball.setMotion(new Vector(BALL_SPEED, 65));
        add(ball);

        Blocks hazard = new Blocks(new Point(getWidth() / 2, 10), getWidth(), 5,Color.black);
        addHazard(hazard);
    }

    /**
     *
     * 리셋 하거나 게임에서 졌을 때 호출된다.
     */
    @Override
    public void resetGame() {
        super.resetGame();
        score = 0;
        boardUpdate();
        if (!targets.isEmpty()) {
            for (Bounds target : targets) {
                    targets.remove(target);
                    if (ball != null) {
                        ball.stop();
                    }
                    super.destroyBounds(target);
            }
        }
    }

    /**
     * 초기 상황판을 만들 때 기본값으로 화면에 띄어준다.
     */
    public void makeBoard() {
        scoreLabel = new JLabel();
        scoreLabel.setText("점수 : " + getScore());
        scoreLabel.setBounds(50, 10, 90, 40);
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(scoreLabel);

        blockOfCountLabel = new JLabel();
        blockOfCountLabel.setText("남은 블럭 : " + getNumOfBlocks());
        blockOfCountLabel.setBounds(scoreLabel.getX()  + 20,
                10, 90, 40);
        blockOfCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(blockOfCountLabel);

    }

    /**
     *
     * 실시간으로 점수 , 남은 블럭을 업데이트 해준다.
     */
    public void boardUpdate() {
        scoreLabel.setText("점수 : " + getScore());
        blockOfCountLabel.setText("남은 블럭 : " + getNumOfBlocks());
    }
}
