package org.example.world;

import org.example.Bounds;
import org.example.Point;
import org.example.block.breakable.OneCollisionBreakableBlocks;
import org.example.block.breakable.TwoCollisionBreakableBlocks;
import org.example.block.unbreakable.UnBreakMovableBlocks;

public class BlockWorld extends BallWorld {

    public BlockWorld(int width, int height) {
        super(width, height);

    }

    @Override
    public void resetGame() {
        super.resetGame();
        if (!targets.isEmpty()) {
            for (Bounds target : targets) {
                boundsList.remove(target);
                super.destroyBounds(target);
            }
        }
    }
    public void makeTrackOne() {
        super.resetGame();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 10; i++) {
                synchronized (boundsList) {
                    Bounds bounds;
                    if (i % 2 == 0) {
                        bounds = new OneCollisionBreakableBlocks(
                                new Point(200 + 55 * i, 500 + j * 25));
                    } else {
                        bounds = new TwoCollisionBreakableBlocks(
                                new Point(200 + 55 * i, 500 + j * 25));
                    }
                    setTarget(bounds);
                }
            }
        }
        UnBreakMovableBlocks unBreakMovableBlocks = new UnBreakMovableBlocks(new Point(450, 400));
        setTarget(unBreakMovableBlocks);
    }
    public void makeRandomTrack() {
        super.resetGame();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 10; i++) {
                synchronized (boundsList) {
                    int randomNum = random.nextInt(2);
                    Bounds bounds;
                    if (randomNum == 1) {
                        bounds = new OneCollisionBreakableBlocks(
                                new Point(200 + 55 * i, 500 + j * 25));
                    } else {
                        bounds = new TwoCollisionBreakableBlocks(
                                new Point(200 + 55 * i, 500 + j * 25));
                    }
                    setTarget(bounds);
                }
            }
        }
        UnBreakMovableBlocks unBreakMovableBlocks = new UnBreakMovableBlocks(new Point(450, 400));
        setTarget(unBreakMovableBlocks);
    }
}

