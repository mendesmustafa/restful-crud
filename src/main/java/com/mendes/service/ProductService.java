package com.mendes.service;

import com.mendes.entity.Product;
import com.mendes.entity.ProductDto;
import com.mendes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mendesmustafa on 06.10.2020.
 */

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAll() {
        List<ProductDto> productDtos = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            ProductDto productDto = new ProductDto();
            convertToProductDto(product, productDto);
            productDtos.add(productDto);
        });
        return productDtos;
    }

    public ProductDto getById(Long id) {
        ProductDto productDto = new ProductDto();
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            convertToProductDto(product.get(), productDto);
            return productDto;
        }
        return null;

    }

    public List<ProductDto> getByName(String name) {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productRepository.findByName(name);
        products.forEach(product -> {
            ProductDto productDto = new ProductDto();
            convertToProductDto(product, productDto);
            productDtos.add(productDto);
        });
        return productDtos;
    }

    private void convertToProductDto(Product product, ProductDto productDto) {
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setQuantity(product.getQuantity());
        productDto.setPrice(product.getPrice());
    }

    public ProductDto update(ProductDto productDto) {
        Optional<Product> product = productRepository.findById(productDto.getId());
        if (product.isPresent()) {
            product.get().setName(productDto.getName());
            product.get().setQuantity(productDto.getQuantity());
            product.get().setPrice(productDto.getPrice());
            productRepository.save(product.get());
            productDto.setId(product.get().getId());
            return productDto;
        }
        return null;
    }

    public void delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
        }
    }

    public ProductDto save(ProductDto productDto) {
        Product product = convertToProduct(productDto);
        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    public List<ProductDto> saveList(List<ProductDto> productDtos) {
        List<Product> products = new ArrayList<>();
        productDtos.forEach(productDto -> {
            Product product = convertToProduct(productDto);
            products.add(product);
        });
        productRepository.saveAll(products);
        for (int i = 0; i < products.size(); i++) {
            productDtos.get(i).setId(products.get(i).getId());
        }
        return productDtos;
    }

    private Product convertToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        return product;
    }
}