package org.example.world;

import org.example.Bounds;
import org.example.bounded.Bounded;

public class BoundedWorld extends MovableWorld {
    public BoundedWorld(int width, int height){
        super(width,height);
    }
    @Override
    public void add(Bounds bounds){
        //볼 마다 충돌 감지를 위한 제약 공간
        synchronized (getBoundsList()){
            for (Bounds other : getBoundsList()) {
                if(other instanceof Bounded){
                    ((Bounded) other).addExcludedBounds(bounds);
                }
            }
        }
        //제약 공간
        if(bounds instanceof Bounded){
            ((Bounded) bounds).setBounds(0,0,getWidth(),getHeight());
            synchronized (getBoundsList()){
                for (Bounds other : getBoundsList()) {
                    ((Bounded) bounds).addExcludedBounds(other);
                }
            }
        }
        super.add(bounds);
    }
}
