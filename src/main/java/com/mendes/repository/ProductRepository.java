package com.mendes.repository;

import com.mendes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mendesmustafa on 06.10.2020.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where lower(trim(p.name)) like lower(concat('%', :name, '%')) ")
    List<Product> findByName(@Param("name") String name);
}