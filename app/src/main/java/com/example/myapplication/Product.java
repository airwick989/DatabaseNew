package com.example.myapplication;

public class Product {

    private String name;
    private double price;
    //We don't need id cause it's in the database and its autoincremented

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
