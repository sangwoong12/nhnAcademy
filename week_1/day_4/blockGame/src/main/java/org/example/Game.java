package org.example;

import org.example.world.BarWorld;

import javax.swing.*;

public class Game extends JFrame {
    final BarWorld world;

    public Game(int width, int height) {

        setSize(width, height);
        setLayout(null);

        world = new BarWorld(getWidth() - 100, getHeight() - 100);
        world.setLocation(50, 60);

        add(world);
        world.setInterval(20);


        JButton startButton = new JButton();
        startButton.setText("게임 시작");
        startButton.setBounds(world.getX(), 10, 90, 40);
        startButton.addActionListener(event -> world.startGame());
        //시작 할경우 포커스를 world로 넘겨 keylistener를 사용할 수 있도록 한다.
        startButton.addActionListener(event -> world.requestFocus());
        startButton.addActionListener(event -> world.setFocusable(true));
        add(startButton);
        JButton resetButton = new JButton();
        resetButton.setText("게임 리셋");
        resetButton.setBounds(startButton.getX() + world.getX() + 50, 10, 90, 40);
        resetButton.addActionListener(event -> world.resetGame());
        add(resetButton);


        JButton trackButton = new JButton();
        trackButton.setText("고정 트랙 생성");
        trackButton.setBounds(resetButton.getX() + world.getX() + 50, 10, 90, 40);
        trackButton.addActionListener(event -> world.makeTrackOne());
        add(trackButton);

        JButton randomTrackButton = new JButton();
        randomTrackButton.setText("랜덤 트랙 생성");
        randomTrackButton.setBounds(trackButton.getX() + world.getX() + 50, 10, 90, 40);
        randomTrackButton.addActionListener(event -> world.makeRandomTrack());
        add(randomTrackButton);

    }

    public void run(long seconds) {
        world.run(seconds);
    }

    public void close() {
        Thread.currentThread().interrupt();
    }

}
