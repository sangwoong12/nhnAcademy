package org.example;


import java.util.Comparator;

public class DesendingOrder implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return Integer.compare(o1.getUserAge(), o2.getUserAge());
    }
}
