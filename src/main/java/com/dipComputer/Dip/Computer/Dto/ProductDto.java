package com.dipComputer.Dip.Computer.Dto;

import lombok.Data;

@Data
public class ProductDto {
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
