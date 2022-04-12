package com.mendes.controller;

import com.mendes.entity.ProductDto;
import com.mendes.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mendesmustafa on 06.10.2020.
 */

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductControl {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<ProductDto>> getByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(productService.getByName(name));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.update(productDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.save(productDto));
    }

    @PostMapping("/save/list")
    public ResponseEntity<List<ProductDto>> saveList(@RequestBody List<ProductDto> productDtos) {
        return ResponseEntity.ok(productService.saveList(productDtos));
    }
}
