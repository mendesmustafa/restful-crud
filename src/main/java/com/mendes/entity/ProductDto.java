package com.mendes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author mendesmustafa on 24-03-2022
 */

@Getter
@Setter
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long id;
    private String name;
    private int quantity;
    private double price;

    public ProductDto(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
