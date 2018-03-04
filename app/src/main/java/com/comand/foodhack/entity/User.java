package com.comand.foodhack.entity;

public class User {
    private String name;
    private int age;
    private double height;
    private double weight;
    private Activity activity;
    private Sex sex;

    private int cal;

    public User(String name, Sex sex, int age, double height, double weight, Activity activity) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activity = activity;

        countCal();
    }

    private void countCal() {
        cal = (int) (((weight * 10) + (height * 6.25) - (age * 5) + sex.getCoef()) * activity.getCoef());
    }

    public int getCal() {
        return cal;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", activity=" + activity +
                ", sex=" + sex +
                ", cal=" + cal +
                '}';
    }

}
