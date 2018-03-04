package com.comand.foodhack;

/**
 * Created by Vyacheslav on 04.03.2018.
 */

public class Person {

    private String userName;
    private int age;
    private int height;
    private int weight;
    private double activityUser;
    private int sex;

    private int cal;

    public Person(String userName, int sex, int age, int height, int weight, double activityUser) {
        this.userName = userName;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activityUser = activityUser;

        countCal();
    }

    private void countCal() {
        cal = (int) (((weight*10)+(height*6.25)-(age*5)+sex)*activityUser);
    }

    public int getCal() {
        return cal;
    }

    public String getUserName() {
        return userName;
    }
}
