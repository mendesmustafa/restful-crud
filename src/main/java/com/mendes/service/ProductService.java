package com.mendes.service;

import com.mendes.entity.Product;
import com.mendes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mendesmustafa on 06.10.2020.
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product getByName(String name) {
        return productRepository.findByName(name);
    }

    public Product update(Product product) {
        Product product1 = productRepository.findById(product.getId()).orElse(null);
        product1.setName(product.getName());
        product1.setQuantity(product.getQuantity());
        product1.setPrice(product.getPrice());
        return productRepository.save(product1);
    }

    public String delete(Long id) {
        productRepository.deleteById(id);
        return "Product deleted" + id;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> saveList(List<Product> products) {
        return productRepository.saveAll(products);
    }
}