package com.comand.foodhack.entity;

import java.util.List;

public class Menu {

    private List<Product> products;
    private MenuType menuType;
    private long weekNumber;

    public Menu(List<Product> products, MenuType menuType, long weekNumber) {
        this.products = products;
        this.menuType = menuType;
        this.weekNumber = weekNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public long getWeekNumber() {
        return weekNumber;
    }
}
