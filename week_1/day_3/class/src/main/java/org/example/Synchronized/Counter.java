package org.example.Synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    long count;
    long anotherCount;

    public Counter(){
        this.count = 0;
        this.anotherCount = 0;
    }
    public void increment(){
        synchronized (this) {++count;}
        ++anotherCount;
    }
    public void decrement(){
        synchronized (this){--count;}
        --anotherCount;
    }
    public long getCount(){
        return count;
    }

    public long getAnotherCount() {
        return anotherCount;
    }
}
