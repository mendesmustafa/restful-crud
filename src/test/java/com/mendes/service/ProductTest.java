package com.mendes.service;

import com.mendes.entity.ProductDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mendesmustafa on 13.12.2020.
 */

@SpringBootTest
class ProductTest {

    private final static String FIRST_NAME = "FIRST-NAME-TEST";
    private final static int FIRST_QUANTITY = 1;
    private final static double FIRST_PRICE = 111.11;

    private final static String SECOND_NAME = "SECOND-NAME-TEST";
    private final static int SECOND_QUANTITY = 2;
    private final static double SECOND_PRICE = 222.22;

    ProductDto firstProductDto;
    ProductDto secondProductDto;
    ProductDto firstResult;
    ProductDto secondResult;

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        firstProductDto = new ProductDto(FIRST_NAME, FIRST_QUANTITY, FIRST_PRICE);
        secondProductDto = new ProductDto(SECOND_NAME, SECOND_QUANTITY, SECOND_PRICE);
    }

    @AfterEach
    public void clear() {
        if (firstResult != null && firstResult.getId() != null) {
            productService.delete(firstResult.getId());
        }
        if (secondResult != null && secondResult.getId() != null) {
            productService.delete(secondResult.getId());
        }
    }

    @Test
    void create() {
        firstResult = productService.save(firstProductDto);
        assertEquals(1, firstResult.getId());
    }

    @Test
    void update() {
        firstResult = productService.save(firstProductDto);
        assertNotNull(firstResult.getId());
        secondProductDto.setId(firstResult.getId());
        ProductDto secondResult = productService.update(secondProductDto);
        assertAll(
                () -> assertEquals(firstResult.getId(), secondResult.getId()),
                () -> assertNotEquals(firstResult.getName(), secondResult.getName()),
                () -> assertNotEquals(firstResult.getPrice(), secondResult.getPrice()),
                () -> assertNotEquals(firstResult.getQuantity(), secondResult.getQuantity())
        );
    }

    @Test
    void findById() {
        firstResult = productService.save(firstProductDto);
        assertNotNull(firstResult.getId());
        ProductDto secondResult = productService.getById(firstResult.getId());
        assertAll(
                () -> assertNotNull(secondResult),
                () -> assertEquals(firstResult.getId(), secondResult.getId()),
                () -> assertEquals(firstResult.getName(), secondResult.getName()),
                () -> assertEquals(firstResult.getQuantity(), secondResult.getQuantity()),
                () -> assertEquals(firstResult.getPrice(), secondResult.getPrice())
        );
    }

    @Test
    void delete() {
        firstResult = productService.save(firstProductDto);
        assertNotNull(firstResult.getId());
        productService.delete(firstResult.getId());
        ProductDto secondResult = productService.getById(firstResult.getId());
        assertNull(secondResult);
    }

    @Test
    void findByName() {
        firstResult = productService.save(firstProductDto);
        assertNotNull(firstResult.getId());
        List<ProductDto> list = productService.getByName(firstProductDto.getName());
        assertEquals(list.get(0).getName(), firstResult.getName());
    }

    @Test
    void getAll() {
        firstResult = productService.save(firstProductDto);
        assertNotNull(firstResult.getId());
        List<ProductDto> list = productService.getAll();
        assertEquals(1, list.size());
    }

    @Test
    void createList() {
        firstResult = productService.save(firstProductDto);
        assertNotNull(firstResult.getId());
        secondResult = productService.save(secondProductDto);
        assertNotNull(secondResult.getId());
        List<ProductDto> list = new ArrayList<>();
        list.add(firstResult);
        list.add(secondResult);
        List<ProductDto> resultList = productService.saveList(list);
        assertEquals(2, resultList.size());
    }
}