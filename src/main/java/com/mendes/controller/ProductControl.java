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
@RequestMapping("product")
public class ProductControl {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @GetMapping("/name/{name}")
    public Product getByName(@PathVariable("name") String name) {
        return productService.getByName(name);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/save/list")
    public List<Product> saveList(@RequestBody List<Product> products) {
        return productService.saveList(products);
    }
}
