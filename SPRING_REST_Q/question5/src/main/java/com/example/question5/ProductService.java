package com.example.question5;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();

    public ProductService() {

        products.put(1L, new Product(1L, "Keyboard", 49.99));

        products.put(2L, new Product(2L, "Mouse", 19.99));

        products.put(3L, new Product(3L, "Monitor", 199.99));
    }

    public boolean deleteProduct(Long id) {

        return products.remove(id) != null;
    }
}