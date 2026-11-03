package com.stockify.controller;

import com.stockify.dao.ProductDAO;
import com.stockify.model.Product;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stock")
public class StockController {

private final ProductDAO productDAO;

public StockController(ProductDAO productDAO) {
    this.productDAO = productDAO;
}

// Display all products and their stock
@GetMapping
public String showStock(Model model) throws SQLException {

    List<Product> products = productDAO.getAllProducts();

    model.addAttribute("products", products);

    return "stock";
}

// Update product quantity
@PostMapping("/update")
public String updateStock(
        @RequestParam int productId,
        @RequestParam int quantity) throws SQLException {

    if (quantity < 0) {
        return "redirect:/stock";
    }

    productDAO.updateStock(productId, quantity);

    return "redirect:/stock";
}


}
