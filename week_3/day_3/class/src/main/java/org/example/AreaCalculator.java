package org.example;

import org.example.lsp.Shape;

public class AreaCalculator {
    public Number sumOfShapes(Shape s1 , Shape s2){
        return s1.getArea().intValue() + s2.getArea().intValue();
    }
}
