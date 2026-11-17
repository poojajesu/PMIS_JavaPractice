package com.example.question1.service;

import com.example.question1.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = Arrays.asList(
            new Product(1L, "Keyboard", 49.99),
            new Product(2L, "Mouse", 19.99)
    );

    public List<Product> getAllProducts() {
        return products;
    }
}