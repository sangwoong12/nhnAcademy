package org.example.block.breakable;

public interface BreakableBlock {
    int getBlockHp();

    void decreaseBlockHp();

    boolean breakable();

    int getScore();
}
