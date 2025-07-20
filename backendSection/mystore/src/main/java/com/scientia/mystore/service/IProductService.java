package com.scientia.mystore.service;

import com.scientia.mystore.dto.ProductDto;
import com.scientia.mystore.entity.Product;

import java.util.List;

public interface IProductService {
    List<ProductDto> getProducts();
}
