package org.example.world;

import org.example.Bounds;
import org.example.Point;
import org.example.block.breakable.OneCollisionBreakableBlocks;
import org.example.block.breakable.TwoCollisionBreakableBlocks;
import org.example.block.unbreakable.UnBreakMovableBlocks;
import org.example.block.unbreakable.UnBreakableBlocks;

/**
 * 벽돌 랜덤 배치, 정해진 배치를 선택하고 생성 담당.
 */
public class BlockWorld extends BallWorld {

    public BlockWorld(int width, int height) {
        super(width, height);

    }

    /**
     * 정해진 배치를 생성할때 사용.
     */
    public void makeTrackOne() {
        super.resetGame();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 10; i++) {
                synchronized (boundsList) {
                    Bounds bounds;
                    if (i % 2 == 0) {
                        bounds = new OneCollisionBreakableBlocks(
                                new Point(200 + 55 * i, 400 + j * 25));
                    } else {
                        bounds = new TwoCollisionBreakableBlocks(
                                new Point(200 + 55 * i, 400 + j * 25));
                    }
                    setTarget(bounds);
                }
            }
        }
        UnBreakMovableBlocks unBreakMovableBlocks = new UnBreakMovableBlocks(new Point(450, 350));
        setTarget(unBreakMovableBlocks);
        UnBreakableBlocks unBreakableBlocks = new UnBreakableBlocks(new Point(450, 200));
        setTarget(unBreakableBlocks);
    }

    /**
     * 랜덤 배치를 생성할때 사용.
     */
    public void makeRandomTrack() {
        super.resetGame();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 10; i++) {
                synchronized (boundsList) {
                    int randomNum = random.nextInt(2);
                    Bounds bounds;
                    if (randomNum == 1) {
                        bounds = new OneCollisionBreakableBlocks(
                                new Point(200 + 55 * i, 400 + j * 25));
                    } else {
                        bounds = new TwoCollisionBreakableBlocks(
                                new Point(200 + 55 * i, 400 + j * 25));
                    }
                    setTarget(bounds);
                }
            }
        }
        UnBreakMovableBlocks unBreakMovableBlocks = new UnBreakMovableBlocks(new Point(450, 350));
        setTarget(unBreakMovableBlocks);
        UnBreakableBlocks unBreakableBlocks = new UnBreakableBlocks(new Point(450, 200));
        setTarget(unBreakableBlocks);
    }
}

