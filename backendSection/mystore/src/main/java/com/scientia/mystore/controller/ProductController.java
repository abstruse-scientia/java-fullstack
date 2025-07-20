package com.scientia.mystore.controller;

import com.scientia.mystore.dto.ProductDto;
import com.scientia.mystore.entity.Product;
import com.scientia.mystore.repository.ProductRepository;
import com.scientia.mystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final IProductService iProductService;


    @GetMapping
    public List<ProductDto> getProducts(){
        return iProductService.getProducts();

    }
}
