package com.mendes.service;

import com.mendes.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mendesmustafa on 13.12.2020.
 */

@SpringBootTest
public class ProductTest {

    private final static String FIRST_NAME = "ali";
    private final static int FIRST_QUANTITY = 3;
    private final static double FIRST_PRICE = 2500.99;

    private final static String SECOND_NAME = "ahmet";
    private final static int SECOND_QUANTITY = 5;
    private final static double SECOND_PRICE = 3999.99;

    Product firstProduct;
    Product secondProduct;

    @Autowired
    ProductService productService;

    @BeforeEach
    public void setUp() {

        firstProduct = new Product();
        firstProduct.setName(FIRST_NAME);
        firstProduct.setQuantity(FIRST_QUANTITY);
        firstProduct.setPrice(FIRST_PRICE);

        secondProduct = new Product();
        secondProduct.setName(SECOND_NAME);
        secondProduct.setQuantity(SECOND_QUANTITY);
        secondProduct.setPrice(SECOND_PRICE);
    }

    @Test
    public void create() {
        firstProduct = productService.save(firstProduct);
        assertNotNull(firstProduct.getId());
    }

    @Test
    public void update() {
        Product firstResult = productService.save(firstProduct);
        secondProduct.setId(firstResult.getId());

        Product secondResult = productService.update(secondProduct);

        assertAll(
                () -> assertEquals(firstResult.getId(), secondResult.getId()),
                () -> assertNotEquals(firstResult.getName(), secondResult.getName()),
                () -> assertNotEquals(firstResult.getPrice(), secondResult.getPrice()),
                () -> assertNotEquals(firstResult.getQuantity(), secondResult.getQuantity())
        );
    }

    @Test
    public void findById() {
        firstProduct = productService.save(firstProduct);
        Product firstResult = productService.getById(firstProduct.getId());

        assertAll(
                () -> assertNotNull(firstResult),
                () -> assertEquals(firstProduct.getId(), firstResult.getId()),
                () -> assertEquals(firstProduct.getName(), firstResult.getName()),
                () -> assertEquals(firstProduct.getQuantity(), firstResult.getQuantity()),
                () -> assertEquals(firstProduct.getPrice(), firstResult.getPrice())
        );
    }

    @Test
    public void delete() {
        firstProduct = productService.save(firstProduct);
        productService.delete(firstProduct.getId());
        Product firstResult = productService.getById(firstProduct.getId());
        assertNull(firstResult);
    }

    @Test
    public void findByName() {
        firstProduct = productService.save(firstProduct);
        Product firstResult = productService.getByName(firstProduct.getName());
        assertNotNull(firstResult);
        assertEquals(firstProduct.getName(), firstResult.getName());
    }
}