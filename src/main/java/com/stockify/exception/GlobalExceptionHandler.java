package com.stockify.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle database and SQL errors
    @ExceptionHandler(java.sql.SQLException.class)
    public String handleSQLException(
            java.sql.SQLException exception, Model model) {

        exception.printStackTrace();

        model.addAttribute("errorMessage",
                "Database error occurred. Please try again.");

        return "error";
    }

    // Handle unexpected errors
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(
            Exception exception, Model model) {

        exception.printStackTrace();

        model.addAttribute("errorMessage",
                "Something went wrong. Please contact the administrator.");

        return "error";
    }
}