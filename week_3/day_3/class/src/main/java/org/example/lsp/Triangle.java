package org.example.lsp;

public class Triangle implements Shape{

    int height;
    int length;

    public Triangle(int height, int length) {
        this.height = height;
        this.length = length;
    }

    @Override
    public Number getArea() {
        return height*length/2;
    }
}
