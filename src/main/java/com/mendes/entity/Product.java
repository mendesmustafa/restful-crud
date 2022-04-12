package com.mendes.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by mendesmustafa on 06.10.2020.
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_NAME")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private double price;
}