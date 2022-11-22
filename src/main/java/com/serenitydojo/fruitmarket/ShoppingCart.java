package com.serenitydojo.fruitmarket;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private static final double WEIGHT_THRESHOLD_IN_KILOGRAMS_FOR_10PERCENT_DISCOUNT = 5.0;
    private final Map<FruitName, Double> fruitsByWeight = new HashMap<>();
    private final Catalog catalog;

    public ShoppingCart(Catalog catalog) {
        this.catalog = catalog;
    }

    public void addFruitByKilogram(FruitName name, double weightInKilograms) {
        if (weightInKilograms < 0.0) {
            throw new IllegalArgumentException("Invalid weight in kilograms " + weightInKilograms);
        }
        if (fruitsByWeight.containsKey(name)) {
            double newWeight = weightInKilograms + fruitsByWeight.get(name);
            fruitsByWeight.replace(name, newWeight);
        } else {
            fruitsByWeight.put(name, weightInKilograms);
        }
    }

    public double getTotal() throws FruitUnavailableException {
        double total = 0.0;
        boolean getsDiscount = false;
        for (Map.Entry<FruitName, Double> fruitWeightPair : fruitsByWeight.entrySet()) {
            if (fruitWeightPair.getValue() >= WEIGHT_THRESHOLD_IN_KILOGRAMS_FOR_10PERCENT_DISCOUNT) {
                getsDiscount = true;
            }
            total += catalog.getPriceOf(fruitWeightPair.getKey()) * fruitWeightPair.getValue();
        }

        if (getsDiscount) {
            total *= 0.9;
        }

        return total;
    }
}
