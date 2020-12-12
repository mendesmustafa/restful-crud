package com.mendes.repository;

import com.mendes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mendesmustafa on 06.10.2020.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}