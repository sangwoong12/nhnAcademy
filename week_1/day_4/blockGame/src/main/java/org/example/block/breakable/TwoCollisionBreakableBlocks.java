package org.example.block.breakable;

import org.example.Point;
import org.example.block.UnMovableBoundedBlocks;

import java.awt.*;

/**
 * 체력 2를 가지고 있는 블록
 */
public class TwoCollisionBreakableBlocks extends UnMovableBoundedBlocks implements BreakableBlock {
    int blockHp;
    public TwoCollisionBreakableBlocks(Point location) {
        super(location, 50, 20, Color.blue);
        blockHp = 2;
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
        return 20;
    }
}
