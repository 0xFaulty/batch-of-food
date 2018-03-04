package com.comand.foodhack.entity;

import java.util.List;

public class Product {

    private String name;
    private double height;
    private List<Ingredient> ingredients;

    public Product(String name, double height, List<Ingredient> ingredients) {
        this.name = name;
        this.height = height;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public class Ingredient {
        private String name;
        private String value;
    }

}
