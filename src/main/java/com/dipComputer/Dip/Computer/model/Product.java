package com.dipComputer.Dip.Computer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String price;
    private String processor;
    private String ram;
    private String storage;
    private String warranty;
    private String description;
    private String productImageUrl;
}