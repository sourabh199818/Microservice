package com.microserviceSourabh.product.service.service;

import com.microserviceSourabh.product.service.dto.ProductRequest;
import com.microserviceSourabh.product.service.dto.ProductResponse;
import com.microserviceSourabh.product.service.model.Product;
import com.microserviceSourabh.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest)
    {
        Product product = Product.builder()
        .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }


    public List<ProductResponse> getAllproducts() {
        List<Product> products = productRepository.findAll();

       return products.stream().map(this::maptoProductResponse).collect(Collectors.toList());
    }

    private ProductResponse maptoProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
