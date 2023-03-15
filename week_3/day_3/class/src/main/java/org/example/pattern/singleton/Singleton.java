package org.example.pattern.singleton;

public class Singleton {
    int number = 0;
    private static Singleton singleton;
    private Singleton(){

    }
    public static Singleton getSingleton(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
    public int getNumber(){
        return number++;
    }
}
