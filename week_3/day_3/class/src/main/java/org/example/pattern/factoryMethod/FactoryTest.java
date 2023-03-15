package org.example.pattern.factoryMethod;

public class FactoryTest {

    public static void main(String[] args) {
        Product cellPhone = new CellphoneFactory().create(111,"Cellphone");
    }

}
