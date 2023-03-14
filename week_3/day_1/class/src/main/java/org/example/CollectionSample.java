package org.example;

import java.util.ArrayList;
import java.util.List;

public class CollectionSample {
    List<String> list = new ArrayList<>();

    public void add(String value) {
        this.list.add(value);
    }

    public void remove(String value) {
        this.list.remove(value);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String s = "aaa";

        for (String value : list) {
            System.out.println(value);
        }
    }


}
