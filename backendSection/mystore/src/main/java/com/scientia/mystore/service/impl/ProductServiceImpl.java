package com.scientia.mystore.service.impl;

import com.scientia.mystore.dto.ProductDto;
import com.scientia.mystore.entity.Product;
import com.scientia.mystore.repository.ProductRepository;
import com.scientia.mystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    @Cacheable("products")
    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(this::transformToDTO).collect(Collectors.toList());
    }

    private ProductDto transformToDTO(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
