package org.example;

import org.example.exam.Lecture;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Lecture<String> Lectures = new Lecture<>(10);
        for(int i = 0; i < 10; i++){
            Lectures.add(i+"님");
        }
        Iterator<String> iterator = Lectures.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
