package org.example.block.breakable;

import org.example.Point;
import org.example.block.UnMovableBoundedBlocks;

import java.awt.*;

public class OneCollisionBreakableBlocks extends UnMovableBoundedBlocks implements BreakableBlock {
    int blockHp;
    public OneCollisionBreakableBlocks(Point location) {
        super(location, 50,20, Color.green);
        blockHp = 1;
    }

    @Override
    public int getBlockHp() {
        return blockHp;
    }

    @Override
    public void decreaseBlockHp() {
        --blockHp;
    }

    @Override
    public boolean breakable() {
        return blockHp == 0;
    }

    @Override
    public int getScore() {
        return 10;
    }
}
