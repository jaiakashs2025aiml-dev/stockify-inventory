package com.stockify.controller;

import com.stockify.dao.ProductDAO;
import com.stockify.model.Product;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ProductController {

    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    // View all products
    @GetMapping("/products")
    public String viewProducts(Model model) throws SQLException {
        List<Product> products = productDAO.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    // Open Add Product page
    @GetMapping("/products/add")
    public String addProductPage() {
        return "product-form";
    }

    // Save new product
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product,
                              Model model) throws SQLException {
        try {
            product.validateProduct();
            productDAO.addProduct(product);
            return "redirect:/products";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "product-form";
        }
    }

    // Open Edit Product page
    @GetMapping("/products/edit/{id}")
    public String editProductPage(@PathVariable int id,
                                  Model model) throws SQLException {
        Product product = productDAO.getProductById(id);

        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        return "product-edit";
    }

    // Update product
    @PostMapping("/products/update")
    public String updateProduct(@ModelAttribute Product product,
                                Model model) throws SQLException {
        try {
            product.validateProduct();
            productDAO.updateProduct(product);
            return "redirect:/products";
        } catch (IllegalArgumentException e) {
            model.addAttribute("product", product);
            model.addAttribute("errorMessage", e.getMessage());
            return "product-edit";
        }
    }

    // Delete product
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable int id)
            throws SQLException {
        productDAO.deleteProduct(id);
        return "redirect:/products";
    }
}