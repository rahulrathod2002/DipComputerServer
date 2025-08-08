package com.dipComputer.Dip.Computer.Dto;

import lombok.Data;

@Data
public class ProductDto {
    private String productName;
    private String price;
    private String processor;
    private String ram;
    private String storage;
    private String warranty;
    private String description;
    private String productImageUrl;
}
