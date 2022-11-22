package com.serenitydojo.fruitmarket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Catalog {

    private final Map<FruitName, Fruit> fruits = new HashMap<>();

    public Fruit setPriceOf(FruitName name) {
        return getOrCreateFruit(name);
    }

    public double getPriceOf(FruitName name)throws FruitUnavailableException{
        if(fruits.containsKey(name)){
            Fruit fruit = fruits.get(name);
            return fruit.getPrice();
        } else
            throw new FruitUnavailableException("Fruit " + name + " is unvailable");

    }
    private Fruit getOrCreateFruit(FruitName name) {
        Fruit fruit;
        if (fruits.containsKey(name)) {
            fruit = fruits.get(name);
        } else {
            fruit = new Fruit(name);
            fruits.put(name, fruit);
        }
        return fruit;
    }



    public List<String> getFruitNames(){
        List<String> fruitNameInAlphabeticalOrder =
                fruits.values().stream().map(fruit -> fruit.getName().toString()).sorted().collect(Collectors.toList());

        return fruitNameInAlphabeticalOrder;
    }
}