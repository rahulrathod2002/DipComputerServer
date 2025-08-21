
package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.Dto.ProductDto;
import com.dipComputer.Dip.Computer.model.Product;
import com.dipComputer.Dip.Computer.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testSaveProduct_withGoogleDriveUrl() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setProductImageUrl("https://drive.google.com/file/d/15kunOoHnvUkcDNlIIFLegzh26DWUbCMY/view?usp=sharing");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setProductImageUrl("https://drive.google.com/uc?export=view&id=15kunOoHnvUkcDNlIIFLegzh26DWUbCMY");

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // When
        Product result = productService.saveProduct(productDto);

        // Then
        assertEquals("https://drive.google.com/uc?export=view&id=15kunOoHnvUkcDNlIIFLegzh26DWUbCMY", result.getProductImageUrl());
    }

    @Test
    public void testSaveProduct_withNormalUrl() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setProductImageUrl("https://example.com/image.jpg");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setProductImageUrl("https://example.com/image.jpg");

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // When
        Product result = productService.saveProduct(productDto);

        // Then
        assertEquals("https://example.com/image.jpg", result.getProductImageUrl());
    }
}
