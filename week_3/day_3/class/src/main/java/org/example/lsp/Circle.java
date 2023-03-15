package org.example.lsp;

public class Circle implements Shape{

    int height;
    int radius;

    public Circle(int height, int radius) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public Number getArea() {
        return height*radius/2*3.14;
    }
}
