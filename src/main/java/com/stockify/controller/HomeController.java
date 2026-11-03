package com.stockify.controller;

import com.stockify.dao.ProductDAO;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductDAO productDAO;

    public HomeController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    // Dashboard
    @GetMapping("/")
    public String home(Model model) throws SQLException {

        model.addAttribute("totalProducts",
                productDAO.getTotalProducts());

        model.addAttribute("availableStock",
                productDAO.getAvailableStock());

        model.addAttribute("lowStockItems",
                productDAO.getLowStockItems());

        return "index";
    }
}