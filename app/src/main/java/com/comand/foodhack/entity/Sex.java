package com.comand.foodhack.entity;


public class Sex {

    private String name;
    private double coef;

    public Sex(String name, double coef) {
        this.name = name;
        this.coef = coef;
    }

    public String getName() {
        return name;
    }

    public double getCoef() {
        return coef;
    }

}
