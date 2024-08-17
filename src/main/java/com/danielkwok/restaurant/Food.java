package com.danielkwok.restaurant;

import java.util.UUID;

public class Food {

    public UUID id;
    public String name;
    public double price;
    public String description;
    public String image;
    public Boolean isSelected;

    public Food(String name, double price, String description, String image) {
        this.id = UUID.randomUUID();
        this.isSelected = false;

        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

}
