package com.example.PizzaApp;

public class Orders {

    private String pizzaName;
    private String price;

    public Orders(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public Orders(String pizzaName, String price) {
        this.pizzaName = pizzaName;
        this.price = price;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
