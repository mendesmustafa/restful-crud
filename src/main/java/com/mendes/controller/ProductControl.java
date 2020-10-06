package com.mendes.controller;

import com.mendes.entity.Product;
import com.mendes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mendesmustafa on 06.10.2020.
 */
@RestController
public class ProductControl {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/product/{id}")
    public Product getById(@PathVariable("id") Long id){
        return productService.getById(id);
    }
    @GetMapping("/product/name/{name}")
    public Product getByName(@PathVariable("name") String name){
        return productService.getByName(name);
    }
    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    @PostMapping("/product/list")
    public List<Product> saveProducts(@RequestBody List<Product> products){
        return productService.saveProducts(products);
    }
}
