package org.example.exam;

import java.util.Iterator;

public class Lecture<E> implements Iterable<E>{
    E[] elements;
    int index;

    public Lecture(int size) {
        this.elements = (E[])new Object[size];
        this.index = 0;
    }
    public void add(E e){
        if( this.index >= elements.length){
            System.out.println("Class is full !");
        } else {
            this.elements[this.index++] = e;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LectureIterator<E>(this);
    }
}
