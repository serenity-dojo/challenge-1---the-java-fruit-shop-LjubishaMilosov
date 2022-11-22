package com.serenitydojo.fruitmarket;

public class FruitUnavailableException extends Exception{


    public FruitUnavailableException(String message) {
        super(message);
    }

    public FruitUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public FruitUnavailableException(Throwable cause) {
        super(cause);
    }
}
