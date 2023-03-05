package org.example.collisionEvent;

import org.example.Bounds;

import java.util.EventObject;

public class CollisionEvent extends EventObject {
    Bounds destination;

    public CollisionEvent(Object source, Bounds destination) {
        super(source);
        this.destination = destination;
    }
    public Bounds getDestination(){
        return destination;
    }

}
