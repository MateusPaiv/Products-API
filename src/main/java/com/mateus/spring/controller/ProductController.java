package com.mateus.spring.controller;


import com.mateus.spring.Dtos.ProductDto;
import com.mateus.spring.model.ProductModel;
import com.mateus.spring.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
   @Autowired
   ProductRepository pr;

   @PostMapping("/products")
    public ResponseEntity<ProductModel> save(@RequestBody @Valid ProductDto productDto){
       var pm = new ProductModel();
       //O que vai ser convertido e o tipo que vai ser convertido
       BeanUtils.copyProperties(productDto,pm);
       return ResponseEntity.status(HttpStatus.CREATED).body(pr.save(pm));

   }

   @GetMapping("products/findAll")
    public List<ProductModel> findAll(){
       return pr.findAll();
   }

    @GetMapping("products/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productModel = pr.findById(id);
        if(productModel.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        return ResponseEntity.status(HttpStatus.OK).body(productModel.get());
 
    }
}
