package com.example.example2;

import android.graphics.Bitmap;

public class Pizza {

    private int id;
    private String pizzaName;
    private String shortDesc;
    private String longDesc;
    private String price;
    private Bitmap image;

    public Pizza(int id, String pizzaName, String shortDesc, String price, Bitmap image) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.shortDesc = shortDesc;
        this.price = price;
        this.image = image;
    }

    public Pizza(int id, String pizzaName, String shortDesc, String longDesc, String price, Bitmap image) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
