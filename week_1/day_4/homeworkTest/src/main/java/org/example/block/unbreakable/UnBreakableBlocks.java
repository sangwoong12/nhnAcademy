package org.example.block.unbreakable;

import org.example.Point;
import org.example.block.UnMovableBoundedBlocks;

import java.awt.*;

public class UnBreakableBlocks extends UnMovableBoundedBlocks {
    public UnBreakableBlocks(Point location) {
        super(location, 50, 20, Color.BLACK);
    }
}
