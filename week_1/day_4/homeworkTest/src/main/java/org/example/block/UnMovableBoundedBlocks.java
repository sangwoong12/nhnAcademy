package org.example.block;

import org.example.Bounded;
import org.example.Bounds;
import org.example.Point;
import org.example.collisionEvent.CollisionEventListener;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.UnaryOperator;

public class UnMovableBoundedBlocks extends Blocks implements Bounded {
    Rectangle bounds;
    List<Bounds> boundsList;
    EventListenerList listenerList;
    public UnMovableBoundedBlocks(Point location, int width, int height, Color color) {
        super(location, width, height, color);
        bounds = new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MIN_VALUE, Integer.MIN_VALUE);
        boundsList = new CopyOnWriteArrayList<>();
        listenerList = new EventListenerList();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public void addExcludedBounds(Bounds bounds) {
        boundsList.add(bounds);
    }

    @Override
    public void coordinateTransformer(UnaryOperator<Point> transformer) {
        super.coordinateTransformer(transformer);
    }

    public void removeExcludedBounds(Bounds bounds){
        boundsList.remove(bounds);
    }

    @Override
    public void addCollisionEventListener(CollisionEventListener listener) {
        listenerList.add(CollisionEventListener.class, listener);
    }

}
