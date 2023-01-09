package com.mservice.productservice.repository;

import com.mservice.productservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
