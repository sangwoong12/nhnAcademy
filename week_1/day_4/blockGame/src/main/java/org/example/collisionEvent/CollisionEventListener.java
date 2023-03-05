package org.example.collisionEvent;

import java.util.EventListener;

public interface CollisionEventListener extends EventListener {
    void collisionEvent(CollisionEvent event);
}
