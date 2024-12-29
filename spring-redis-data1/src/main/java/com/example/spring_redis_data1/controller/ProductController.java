package com.example.spring_redis_data1.controller;

import com.example.spring_redis_data1.entity.Product;
import com.example.spring_redis_data1.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    // Save product
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productDao.save(product);
    }

    // Get all products
    @GetMapping
    public List<Object> getAllProducts() {
        return productDao.findAll();
    }

    // Get product by id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDao.findProductById(id);
    }

    // Delete product by id
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productDao.deleteProduct(id);
    }
}
