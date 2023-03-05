package org.example.bounded;


import org.example.*;
import org.example.Point;
import org.example.collisionEvent.CollisionEvent;
import org.example.collisionEvent.CollisionEventListener;
import org.example.movable.MovableBall;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class BoundedImpl extends MovableBall implements Bounded {
    Rectangle bounds;
    long interval;
    List<Bounds> boundsList;
    EventListenerList listenerList;

    public BoundedImpl(Point location, int width, int height, Color color) {
        super(location, width,height, color);
        interval = 100;
        bounds = new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MIN_VALUE, Integer.MIN_VALUE);
        boundsList = new LinkedList<>();
        listenerList = new EventListenerList();
    }

    public void setBounds(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public void addExcludedBounds(Bounds bounds) {
        boundsList.add(bounds);
    }

    public void next() {
        super.next();

        if ((getLocation().getX() - getRadius() < bounds.getX())
                || (bounds.getX() + bounds.getWidth() < getLocation().getX() + getRadius())) {
            getMotion().flipX();
        }

        if ((getLocation().getY() - getRadius() < bounds.getY())
                || (bounds.getY() + bounds.getHeight() < getLocation().getY() + getRadius())) {
            getMotion().flipY();
        }
        synchronized (boundsList) {
            for (Bounds bounds : boundsList) {
                if (isCollision(bounds)) {
                    Rectangle intersection = getIntersection(bounds);
                    if ((getWidth() == intersection.getWidth())
                            || (bounds.getWidth() == intersection.getWidth())) {
                        getMotion().flipY();
                    } else if ((getHeight() == intersection.getHeight())
                            || (bounds.getHeight() == intersection.getHeight())) {
                        getMotion().flipX();
                    } else {
                        getMotion().flipY();
                        getMotion().flipX();
                    }

                    CollisionEventListener[] listeners = listenerList.getListeners(CollisionEventListener.class);
                    for (int i = 0; i < listeners.length; i++) {
                        listeners[i].collisionEvent(new CollisionEvent(this, bounds));
                    }
                }
            }
        }
    }

    public void addCollisionEventListener(CollisionEventListener listener) {
        listenerList.add(CollisionEventListener.class, listener);
    }

}
