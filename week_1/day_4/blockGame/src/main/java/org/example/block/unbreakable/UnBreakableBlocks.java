package org.example.block.unbreakable;

import org.example.Point;
import org.example.block.UnMovableBoundedBlocks;

import java.awt.*;

/**
 * 부서지지 않고 못 움직이는 블록
 */
public class UnBreakableBlocks extends UnMovableBoundedBlocks {
    public UnBreakableBlocks(Point location) {
        super(location, 50, 20, Color.BLACK);
    }
}
