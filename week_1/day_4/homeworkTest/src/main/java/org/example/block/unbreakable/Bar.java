package org.example.block.unbreakable;

import org.example.Bounds;
import org.example.Point;
import org.example.block.BoundedBlocks;

import java.awt.*;

public class Bar extends BoundedBlocks {
    Rectangle bounds;
    Bounds bar;
    public Bar(Point location) {
        super(location, 100, 20, Color.magenta);
        bounds = new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MIN_VALUE, Integer.MIN_VALUE);

    }
    public void next() {
        super.next();
    }
}
