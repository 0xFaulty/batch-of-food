package com.comand.foodhack.entity;


public class Activity {

    private String name;
    private double coef;

    public Activity(String name, double coef) {
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
