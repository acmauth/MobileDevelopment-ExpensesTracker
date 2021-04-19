package com.example.expensestracker;

public class Product {
    private int id;
    private String name,category;
    private int quantity;
    private int price;


    public Product(){}

    public Product(int id,String name,int quantity,int price){
        this.id=id;
        this.name=name;
        this.quantity=quantity;
        this.price=price;
        this.category=category;
    }
    public Product(int price,String category){
        this.price=price;
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
