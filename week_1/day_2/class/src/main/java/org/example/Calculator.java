package org.example;


public class Calculator {
    private int memory;
    public synchronized void setMemory(int value) {

        this.memory = value;
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":" + this.memory);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } }
}
class User extends Thread{
    private Calculator calculator;
    int memory;

    public User(String name, int memory){
        this.setName(name);
        this.memory = memory;
    }
    public void setCalculator(Calculator calculator){

        this.calculator = calculator;
    }
    @Override
    public void run(){
            calculator.setMemory(this.memory);
    }
}
class Main{
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        User user1 = new User("user1", 100);
        User user2 = new User("user2", 50);

        user1.setCalculator(calculator);
        user1.start();
        user2.setCalculator(calculator);
        user2.start();

    }
}