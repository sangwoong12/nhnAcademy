package org.example.block.unbreakable;

import org.example.Point;
import org.example.Vector;
import org.example.block.BoundedBlocks;

import java.awt.*;

public class UnBreakMovableBlocks extends BoundedBlocks {
    public UnBreakMovableBlocks(Point location) {
        super(location, 50,20,Color.BLACK);
        this.setMotion(new Vector(3,180));
    }
}
