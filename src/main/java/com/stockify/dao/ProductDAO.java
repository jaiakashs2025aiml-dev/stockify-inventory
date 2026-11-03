
package com.stockify.dao;

import com.stockify.config.DBConnection;
import com.stockify.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {

    private final DBConnection dbConnection;

    public ProductDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Display all products
    public List<Product> getAllProducts() throws SQLException {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("category_id"));

                products.add(product);
            }
        }

        return products;
    }

    // Add a new product to MySQL
    public void addProduct(Product product) throws SQLException {

        String sql = "INSERT INTO products "
                + "(product_name, price, quantity, category_id) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getCategoryId());

            ps.executeUpdate();
        }
    }
    // Update product stock quantity
public void updateStock(int productId, int quantity) throws SQLException {

    String sql = "UPDATE products SET quantity = ? WHERE product_id = ?";

    try (Connection con = dbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, quantity);
        ps.setInt(2, productId);

        ps.executeUpdate();
    }
}
// Get total number of products
public int getTotalProducts() throws SQLException {

    String sql = "SELECT COUNT(*) FROM products";

    try (Connection con = dbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }
    }

    return 0;
}

// Get total available stock
public int getAvailableStock() throws SQLException {

    String sql = "SELECT COALESCE(SUM(quantity), 0) FROM products";

    try (Connection con = dbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }
    }

    return 0;
}

// Get number of low stock products
public int getLowStockItems() throws SQLException {

    String sql = "SELECT COUNT(*) FROM products WHERE quantity <= 5";

    try (Connection con = dbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }
    }

    return 0;
}

// Edit product details
public void updateProduct(Product product) throws SQLException {

    String sql = "UPDATE products SET product_name = ?, price = ?, quantity = ?, category_id = ? WHERE product_id = ?";

    try (Connection con = dbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, product.getProductName());
        ps.setDouble(2, product.getPrice());
        ps.setInt(3, product.getQuantity());
        ps.setInt(4, product.getCategoryId());
        ps.setInt(5, product.getProductId());

        ps.executeUpdate();
    }
}

// Delete a product
public void deleteProduct(int productId) throws SQLException {

    String sql = "DELETE FROM products WHERE product_id = ?";

    try (Connection con = dbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, productId);
        ps.executeUpdate();
    }
}
// Get a single product by ID
public Product getProductById(int productId) throws SQLException {

    String sql = "SELECT * FROM products WHERE product_id = ?";

    try (Connection con = dbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, productId);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("category_id"));

                return product;
            }
        }
    }

    return null;
}


}

