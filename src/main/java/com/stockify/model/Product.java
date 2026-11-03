package com.stockify.model;
// Inheritance: Product extends InventoryItem
public class Product extends InventoryItem {

    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // Polymorphism: overriding the abstract method
    @Override
    public String getItemType() {
        return "Product";
    }
        // Validate product details
    public void validateProduct() {

        if (getProductName() == null || getProductName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }

        if (getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        if (getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        if (getCategoryId() <= 0) {
            throw new IllegalArgumentException("Enter a valid Category ID.");
        }
    }
}