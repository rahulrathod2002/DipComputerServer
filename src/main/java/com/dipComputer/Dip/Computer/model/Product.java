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
    private int price;
    private String processor;
    private double screenSizeInInch;
    private int ramInGB;
    private String os;
    private int storageCapacityGB;
    private int warrantyYears;
    private boolean includesBatteryWarranty;
    private boolean includesAdapterWarranty;
    private boolean isTouchscreen;
    private boolean hasWebcam;
    private String productCondition;
    private String imageUrl;
}