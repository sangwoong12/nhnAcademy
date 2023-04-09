package com.nhnacademy.nhnmart.item;

import java.util.Objects;

public class Food {
    private final String name;
    private final int price;
    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Food food = (Food) obj;
        return Objects.equals(name, food.name) && price == food.price;
    }
}
