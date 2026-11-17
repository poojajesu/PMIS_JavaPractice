package com.example.question3;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();

    private long nextId = 1;

    public ProductService() {

        products.put(1L, new Product(1L, "Keyboard", 49.99));
        products.put(2L, new Product(2L, "Mouse", 19.99));

        nextId = 3;
    }

    public Product createProduct(Product product) {

        product.setId(nextId);

        products.put(nextId, product);

        nextId++;

        return product;
    }
}