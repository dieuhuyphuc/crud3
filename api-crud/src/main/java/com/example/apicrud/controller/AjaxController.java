package com.example.apicrud.controller;


import com.example.apicrud.Entity.ProductEntity;
import com.example.apicrud.repository.ProductRepo;
import com.example.apicrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@CrossOrigin(origins = "*")
//@CrossOrigin("*")
@RequestMapping("/product/v1")
public class AjaxController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepo productRepo;

    @RequestMapping(method = RequestMethod.GET, path = {"/all"})
    public ResponseEntity<?> index(){
        return ResponseEntity.ok(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/create"})
    public ResponseEntity<?> create(@Valid @RequestBody ProductEntity product){
        return ResponseEntity.ok(productService.save(product));
    }


    @RequestMapping(method = RequestMethod.GET, path = {"/sell/{id}"})
    public ResponseEntity<ProductEntity> update(@PathVariable int id){

        Optional<ProductEntity> optionalProduct = productService.findById(id)
                ;
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        ProductEntity product = optionalProduct.get();
        product.setQuantity(product.getQuantity() -1);
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int id){

        ProductEntity product = productService.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        productRepo.delete(product);
        System.out.println("success");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
