package org.example.world;

import org.example.Bounded;
import org.example.Bounds;

/**
 * bounds 들의 접촉을 담당.
 */
public class BoundedWorld extends MovableWorld {
    public BoundedWorld(int width, int height) {
        super(width, height);
    }

    /**
     * 블럭 파괴시 리스트에서 해당 블록 삭제.
     * @param bounds
     */
    public void destroyBounds(Bounds bounds) {
        synchronized (boundsList) {
            for (Bounds other : boundsList) {
                if (other instanceof Bounded) {
                    ((Bounded) other).removeExcludedBounds(bounds);
                }
            }
        }
    }

    @Override
    public void add(Bounds bounds) {
        //볼 마다 충돌 감지를 위한 제약 공간
        synchronized (boundsList) {
            for (Bounds other : boundsList) {
                if (other instanceof Bounded) {
                    ((Bounded) other).addExcludedBounds(bounds);
                }
            }
        }
        //제약 공간
        if (bounds instanceof Bounded) {
            ((Bounded) bounds).setBounds(0, 0, getWidth(), getHeight());
            synchronized (boundsList) {
                for (Bounds other : boundsList) {
                    ((Bounded) bounds).addExcludedBounds(other);
                }
            }
        }
        super.add(bounds);
    }

}
