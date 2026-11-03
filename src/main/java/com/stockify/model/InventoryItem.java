package com.stockify.model;
// Abstraction: InventoryItem is an abstract class
public abstract class InventoryItem {

    // Encapsulation: private variables
    private int productId;
    private String productName;
    private double price;
    private int quantity;

    // Constructor
    public InventoryItem() {
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Abstract method
    public abstract String getItemType();
}