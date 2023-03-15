package org.example.pattern.singleton;

public class SingletonTest {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println(singleton.getNumber());
        System.out.println(singleton.getNumber());
        System.out.println(singleton.getNumber());
        Singleton singleton2 = Singleton.getSingleton();
        System.out.println(singleton2.getNumber());
    }

}
