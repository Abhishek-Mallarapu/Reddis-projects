package com.example.spring_redis_data1.repository;

import com.example.spring_redis_data1.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    public static final String HASH_KEY = "Product";

    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    // Save product to Redis hash
    public Product save(Product product) {
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    // Get all products from Redis hash
    public List<Object> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    // Find product by ID
    public Product findProductById(int id) {
        return (Product) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    // Delete product by ID
    public String deleteProduct(int id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "Product removed!";
    }
}
