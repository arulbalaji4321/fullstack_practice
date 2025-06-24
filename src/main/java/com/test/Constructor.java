package com.test;

public class Constructor {

    // User info fields
    String name;
    int age;
    String region;

    // Product-related fields (private for encapsulation)
    private String purchaseProduct;
    private double productPrice;
    private double profitOfTheDay;
    private int totalProductsPurchased;

    // Constructor to initialize user details
    public Constructor(String name, int age, String region) {
        this.age = age;
        this.name = name;
        this.region = region;
    }

    // Getter for product name
    public String getProductName() {
        return purchaseProduct;
    }

    // Getter for product price
    public double getProductPrice() {
        return productPrice;
    }

    // Getter for profit of the day (not used yet in App)
    public double getProfitOfTheDay() {
        return profitOfTheDay;
    }

    // Setter for product name
    public void setProductName(String purchaseProduct) {
        this.purchaseProduct = purchaseProduct;
    }

    // Setter for product price
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    // Setter for profit of the day
    public void setProfitOfTheDay(double profitOfTheDay) {
        this.profitOfTheDay = profitOfTheDay;
    }

    // Setter for total products purchased
    public void setTotalProductsPurchased(int totalProductsPurchased) {
        this.totalProductsPurchased = totalProductsPurchased;
    }

    // Getter for total products purchased
    public int getTotalProductsPurchased() {
        return totalProductsPurchased;
    }
}
