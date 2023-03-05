package org.example;

import org.example.world.BallWorld;
import org.example.world.BarWorld;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JFrame {
    final BarWorld world;
    JTextField scoreField;
    JTextField blockOfCountField;

    public Game(int width, int height){
        super();


        setSize(width,height);
        setLayout(null);

        world = new BarWorld(getWidth() - 100,getHeight() - 100);
        world.setLocation(50,60);

        add(world);
        world.setInterval(20);


        //TODO 1: 하단 바 접속시 게임 종료
        JButton startButton = new JButton();
        startButton.setText("게임 시작");
        startButton.setBounds(world.getX() ,
                10, 90, 40);
        startButton.addActionListener(event-> world.startGame());
        add(startButton);
        //TODO 2: 게임 시작

        //TODO 3: 게임 종류 ( 벽돌 배치 , 랜덤 생성 )
        JButton resetButton = new JButton();
        resetButton.setText("게임 리셋");
        resetButton.setBounds(startButton.getX()+world.getX()+50 ,
                10, 90, 40);
        resetButton.addActionListener(event-> world.resetGame());
        add(resetButton);


        JButton trackButton = new JButton();
        trackButton.setText("고정 트랙 생성");
        trackButton.setBounds(resetButton.getX()+world.getX()+50
                , 10, 90, 40);
        trackButton.addActionListener(event-> world.makeTrackOne());
        add(trackButton);

        JButton randomTrackButton = new JButton();
        randomTrackButton.setText("랜덤 트랙 생성");
        randomTrackButton.setBounds(trackButton.getX() + world.getX() + 50
                , 10, 90, 40);
        randomTrackButton.addActionListener(event-> world.makeRandomTrack());
        add(randomTrackButton);
        //TODO 4: 상황판 (점수 , 볼이 튕겨진 횟수 , 남은 벽돌 수)
        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("점수 : ");
        scoreLabel.setBounds(randomTrackButton.getX() + world.getX() + 50
                , 10, 90, 40);
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(scoreLabel);

        scoreField = new JTextField();
        scoreField.setBounds(scoreLabel.getX() + scoreLabel.getWidth() + 10, scoreLabel.getY()
                , 90, 40);
        scoreField.setText(String.valueOf(world.getScore()));
        add(scoreField);

        JLabel blockOfCountLabel = new JLabel();
        blockOfCountLabel.setText("남은 블럭 : ");
        blockOfCountLabel.setBounds(scoreField.getX() + world.getX() + 20
                , 10, 90, 40);
        blockOfCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(blockOfCountLabel);

        blockOfCountField = new JTextField();
        blockOfCountField.setBounds(blockOfCountLabel.getX() + blockOfCountLabel.getWidth() + 10
                , blockOfCountLabel.getY() , 90, 40);
        blockOfCountField.setText(String.valueOf(world.getNumOfBlocks()));
        add(blockOfCountField);

        super.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { //Key Event
                if( e.getKeyCode() == KeyEvent.VK_LEFT ) {
                    world.barUse(e);
                    System.out.println("Pressed Left Key");
                }
                else if( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                    world.barUse(e);
                    System.out.println("Pressed Right Key");
                }
            }
        });
    }
    public void run(long seconds){
        world.run(seconds);
    }
    public void close(){
        Thread.currentThread().interrupt();
    }
}
