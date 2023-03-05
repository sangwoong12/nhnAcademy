package org.example.block;

import org.example.Bounded;
import org.example.Bounds;
import org.example.Point;
import org.example.Vector;
import org.example.collisionEvent.CollisionEvent;
import org.example.collisionEvent.CollisionEventListener;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.UnaryOperator;

public class BoundedBlocks extends MovableBlocks implements Bounded {
    Rectangle bounds;
    List<Bounds> boundsList;
    EventListenerList listenerList;

    public BoundedBlocks(Point location, int width, int height, Color color) {
        super(location, width, height, color);

        bounds = new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MIN_VALUE, Integer.MIN_VALUE);
        boundsList = new CopyOnWriteArrayList<>();
        listenerList = new EventListenerList();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }


    public void coordinateTransformer(UnaryOperator<Point> transformer) {
        super.coordinateTransformer(transformer);
    }


    public Vector getMotion() {
        return super.getMotion();
    }

    public void addEffect(Vector effect) {
        super.addEffect(effect);
    }


    public void draw(Graphics g) {
        super.draw(g);
    }


    public void run() {
        super.run();
    }

    @Override
    public void addExcludedBounds(Bounds bounds) {
        boundsList.add(bounds);
    }
    @Override
    public void removeExcludedBounds(Bounds bounds){
        boundsList.remove(bounds);
    }

    public void next() {
        super.next();

        if ((getLocation().getX() - getWidth() / 2 < bounds.getX())
                || (bounds.getX() + bounds.getWidth() < getLocation().getX() + getWidth() / 2)) {
            motion.flipX();
        }

        if ((getLocation().getY() - getHeight() / 2 < bounds.getY())
                || (bounds.getY() + bounds.getHeight() < getLocation().getY() + getHeight() / 2)) {
            motion.flipY();
        }
        synchronized (boundsList) {
            for (Bounds bounds : boundsList) {
                if (isCollision(bounds)) {
                    Rectangle intersection = getIntersection(bounds);
                    if ((getWidth() == intersection.getWidth())
                            || (bounds.getWidth() == intersection.getWidth())) {
                        motion.flipY();
                    } else if ((getHeight() == intersection.getHeight())
                            || (bounds.getHeight() == intersection.getHeight())) {
                        motion.flipX();
                    } else {
                        motion.flipY();
                        motion.flipX();
                    }
                    System.out.println("움직이지 않는 블럭 충돌");
                    CollisionEventListener[] listeners = listenerList.getListeners(CollisionEventListener.class);
                    for (int i = 0; i < listeners.length; i++) {
                        listeners[i].collisionEvent(new CollisionEvent(this, bounds));
                    }
                }
            }
        }
    }

    @Override
    public void addCollisionEventListener(CollisionEventListener listener) {
        listenerList.add(CollisionEventListener.class, listener);
    }

}
