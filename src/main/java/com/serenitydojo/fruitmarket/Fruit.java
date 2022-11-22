package com.serenitydojo.fruitmarket;

public class Fruit {


    private final FruitName name;
    private double price;

    public Fruit(FruitName name) {
        this.name = name;

    }

    public FruitName getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void to(double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("Invalid price: " + price);
        }
        this.price = price;
    }


}
