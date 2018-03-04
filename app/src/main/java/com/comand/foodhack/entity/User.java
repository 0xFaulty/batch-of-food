package com.comand.foodhack.entity;

public class User {
    private String userName;
    private int age;
    private int height;
    private int weight;
    private Activity activity;
    private Sex sex;

    private int cal;

    public User(String userName, Sex sex, int age, int height, int weight, Activity activity) {
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }
}
