package com.example.PizzaApp;

public class OrderForCourier {

    private String phone;
    private String pizzaPlace;
    private String pizzaList;
    private String price;
    private String address;

    public OrderForCourier(String phone, String pizzaPlace, String pizzaList, String price, String address) {
        this.phone = phone;
        this.pizzaPlace = pizzaPlace;
        this.pizzaList = pizzaList;
        this.price = price;
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPizzaPlace() {
        return pizzaPlace;
    }

    public void setPizzaPlace(String pizzaPlace) {
        this.pizzaPlace = pizzaPlace;
    }

    public String getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(String pizzaList) {
        this.pizzaList = pizzaList;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
