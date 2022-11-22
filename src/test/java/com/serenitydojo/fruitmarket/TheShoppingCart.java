package com.serenitydojo.fruitmarket;

import org.junit.Before;
import org.junit.Test;

import static com.serenitydojo.fruitmarket.FruitName.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TheShoppingCart {

    Catalog catalog;
    private ShoppingCart shoppingCart;

    @Before
    public void setup() {
        this.catalog = new Catalog();
        this.catalog.setPriceOf(APPLE).to(4.00);
        this.catalog.setPriceOf(ORANGE).to(5.5);
        this.catalog.setPriceOf(BANANA).to(6.0);
        this.catalog.setPriceOf(PEAR).to(4.5);
        this.shoppingCart = new ShoppingCart(this.catalog);
    }

    @Test
    public void shouldCalculateCorrectTotalWhenAddingFruitsToCart() throws FruitUnavailableException {
        // Given
        double weightOfBananasInKilograms = 1.2;
        this.shoppingCart.addFruitByKilogram(BANANA, weightOfBananasInKilograms);

        // When
        double currentTotal = this.shoppingCart.getTotal();

        // Then
        assertThat(currentTotal).isEqualTo(weightOfBananasInKilograms * catalog.getPriceOf(BANANA));
    }

    @Test
    public void shouldCalculateCorrectTotalWhenAddingMoreOfSameFruitsToCart() throws FruitUnavailableException {
        // Given
        double initialWeightOfBananasInKilograms = 1.2;
        this.shoppingCart.addFruitByKilogram(BANANA, initialWeightOfBananasInKilograms);
        double additionalWeightOfBananasInKilograms = 0.6;
        this.shoppingCart.addFruitByKilogram(BANANA, additionalWeightOfBananasInKilograms);


        // When
        double currentTotal = this.shoppingCart.getTotal();

        // Then
        assertThat(currentTotal)
                .isEqualTo((initialWeightOfBananasInKilograms + additionalWeightOfBananasInKilograms) * catalog.getPriceOf(BANANA));
    }

    @Test
    public void shouldCalculateCorrectTotalWhenAddingDifferentTypesOfFruitsToCart() throws FruitUnavailableException {
        // Given
        double weightOfBananasInKilograms = 1.2;
        this.shoppingCart.addFruitByKilogram(BANANA, weightOfBananasInKilograms);
        double weightOfApplesInKilograms = 0.6;
        this.shoppingCart.addFruitByKilogram(APPLE, weightOfApplesInKilograms);


        // When
        double currentTotal = this.shoppingCart.getTotal();

        // Then
        assertThat(currentTotal)
                .isEqualTo(weightOfBananasInKilograms * this.catalog.getPriceOf(BANANA) + weightOfApplesInKilograms * this.catalog.getPriceOf(APPLE));
    }

    @Test
    public void shouldGet10PercentDiscountWhenAdding5KilosOfAnyFruit() throws FruitUnavailableException {
        // Given
        double weightOfBananasInKilograms = 1.2;
        this.shoppingCart.addFruitByKilogram(BANANA, weightOfBananasInKilograms);
        double weightOfApplesInKilograms = 5.0;
        this.shoppingCart.addFruitByKilogram(APPLE, weightOfApplesInKilograms);


        // When
        double currentTotal = this.shoppingCart.getTotal();

        // Then
        assertThat(currentTotal)
                .isEqualTo(0.9 * (weightOfBananasInKilograms * this.catalog.getPriceOf(BANANA) + weightOfApplesInKilograms * this.catalog.getPriceOf(APPLE)));
    }

    @Test
    public void shouldGet10PercentDiscountWhenAddingMoreThan5KilosOfAnyFruit() throws FruitUnavailableException {
        // Given
        double weightOfBananasInKilograms = 5.1;
        this.shoppingCart.addFruitByKilogram(BANANA, weightOfBananasInKilograms);
        double weightOfApplesInKilograms = 1.2;
        this.shoppingCart.addFruitByKilogram(APPLE, weightOfApplesInKilograms);


        // When
        double currentTotal = this.shoppingCart.getTotal();

        // Then
        assertThat(currentTotal)
                .isEqualTo(0.9 * (weightOfBananasInKilograms * this.catalog.getPriceOf(BANANA) + weightOfApplesInKilograms * this.catalog.getPriceOf(APPLE)));
    }

    @Test
    public void shouldGet10PercentDiscountWhenAddingMoreThan10KilosOfAnyFruit() throws FruitUnavailableException {
        // Given
        double weightOfBananasInKilograms = 5.2;
        this.shoppingCart.addFruitByKilogram(BANANA, weightOfBananasInKilograms);
        double weightOfApplesInKilograms = 5.01;
        this.shoppingCart.addFruitByKilogram(APPLE, weightOfApplesInKilograms);


        // When
        double currentTotal = this.shoppingCart.getTotal();

        // Then
        assertThat(currentTotal)
                .isEqualTo(0.9 * (weightOfBananasInKilograms * this.catalog.getPriceOf(BANANA) + weightOfApplesInKilograms * this.catalog.getPriceOf(APPLE)));
    }

    @Test
    public void shouldThrowAIllegalArgumentExceptionWhenAddingFruitWithNegativeWeight() {
        // Then
        assertThatThrownBy(() -> this.shoppingCart.addFruitByKilogram(BANANA, -0.5))
                .isInstanceOf(IllegalArgumentException.class);
    }
}