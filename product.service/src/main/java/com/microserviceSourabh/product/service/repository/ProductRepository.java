package com.microserviceSourabh.product.service.repository;

import com.microserviceSourabh.product.service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String >  {

}
