package org.example.exam;


import java.util.Iterator;

public class LectureIterator<E> implements Iterator<E> {
    Lecture<E> iterable;
    int index = 0;

    public LectureIterator(Lecture<E> iterable) {
        this.iterable = iterable;
    }

    @Override
    public boolean hasNext() {
        return this.index < iterable.elements.length;
    }

    @Override
    public E next() {
        return iterable.elements[this.index++];
    }
}
