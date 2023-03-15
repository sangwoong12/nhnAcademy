package org.example.lsp;

public class Square implements Shape {
    int length;

    public Square(int length) {
        this.length = length;
    }

    @Override
    public Number getArea() {
        return length*length;
    }
}
