package org.example.world;


import org.example.Bounds;
import org.example.Drawable;
import org.example.Point;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class World extends JPanel {
    final List<Bounds> boundsList;
    static Random random = new Random();
    public World(int width, int height) {
        super();
        boundsList = new CopyOnWriteArrayList<>();
        setSize(width, height);
    }

    public void add(Bounds bounds) {
        if (bounds instanceof Drawable) {
            ((Drawable) bounds)
                    .coordinateTransformer(p -> new Point(p.getX(), getHeight() - p.getY()));
        }
        boundsList.add(bounds);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(0, 0, getWidth(), getHeight());
        synchronized (boundsList) {
            for (Bounds bounds : boundsList) {
                if (bounds instanceof Drawable) {
                    ((Drawable) bounds).draw(g);
                }
            }
        }
    }
}
