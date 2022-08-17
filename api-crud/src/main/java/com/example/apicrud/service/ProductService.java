package com.example.apicrud.service;

import com.example.apicrud.Entity.BaseResponse;
import com.example.apicrud.Entity.ProductEntity;
import com.example.apicrud.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public BaseResponse<ProductEntity> findAll(){
        return new BaseResponse(HttpStatus.OK,"success", productRepo.findAll());
    }

    public Optional<ProductEntity> findById(int id){
        return productRepo.findById(id);
    }
    public ProductEntity save(ProductEntity product){
        return productRepo.save(product);
    }

    public void delete(ProductEntity product){
        productRepo.delete(product);
    }
}
