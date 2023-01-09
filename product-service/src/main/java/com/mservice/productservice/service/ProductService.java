package com.mservice.productservice.service;

import com.mservice.productservice.dto.ProductRequest;
import com.mservice.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
