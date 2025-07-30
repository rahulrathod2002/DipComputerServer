package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.model.Product;
import com.dipComputer.Dip.Computer.Dto.ProductDto;
import com.dipComputer.Dip.Computer.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setProductName("Laptop X1");
        product1.setPrice(1200);
        product1.setProcessor("Intel i7");
        product1.setScreenSizeInInch(15.6);
        product1.setRamInGB(16);
        product1.setOs("Windows 11");
        product1.setStorageCapacityGB(512);
        product1.setWarrantyYears(2);
        product1.setIncludesBatteryWarranty(true);
        product1.setIncludesAdapterWarranty(true);
        product1.setTouchscreen(false);
        product1.setHasWebcam(true);
        product1.setProductCondition("New");
        product1.setImageUrl("http://example.com/laptopx1.jpg");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("Mouse Pro");
        product2.setPrice(25);
        product2.setProcessor("N/A");
        product2.setScreenSizeInInch(0.0);
        product2.setRamInGB(0);
        product2.setOs("N/A");
        product2.setStorageCapacityGB(0);
        product2.setWarrantyYears(1);
        product2.setIncludesBatteryWarranty(false);
        product2.setIncludesAdapterWarranty(false);
        product2.setTouchscreen(false);
        product2.setHasWebcam(false);
        product2.setProductCondition("Used");
        product2.setImageUrl("http://example.com/mousepro.jpg");

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Laptop X1", result.get(0).getProductName());
        assertEquals(1200, result.get(0).getPrice());
        assertEquals("Intel i7", result.get(0).getProcessor());
        assertEquals(15.6, result.get(0).getScreenSizeInInch());
        assertEquals(16, result.get(0).getRamInGB());
        assertEquals("Windows 11", result.get(0).getOs());
        assertEquals(512, result.get(0).getStorageCapacityGB());
        assertEquals(2, result.get(0).getWarrantyYears());
        assertEquals(true, result.get(0).isIncludesBatteryWarranty());
        assertEquals(true, result.get(0).isIncludesAdapterWarranty());
        assertEquals(false, result.get(0).isTouchscreen());
        assertEquals(true, result.get(0).isHasWebcam());
        assertEquals("New", result.get(0).getProductCondition());
        assertEquals("http://example.com/laptopx1.jpg", result.get(0).getImageUrl());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductByIdFound() {
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Laptop X1");
        product.setPrice(1200);
        product.setProcessor("Intel i7");
        product.setScreenSizeInInch(15.6);
        product.setRamInGB(16);
        product.setOs("Windows 11");
        product.setStorageCapacityGB(512);
        product.setWarrantyYears(2);
        product.setIncludesBatteryWarranty(true);
        product.setIncludesAdapterWarranty(true);
        product.setTouchscreen(false);
        product.setHasWebcam(true);
        product.setProductCondition("New");
        product.setImageUrl("http://example.com/laptopx1.jpg");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals("Laptop X1", result.getProductName());
        assertEquals(1200, result.getPrice());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Product result = productService.getProductById(1L);

        assertNull(result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setProductName("Keyboard Pro");
        productDto.setPrice(75);
        productDto.setProcessor("N/A");
        productDto.setScreenSizeInInch(0.0);
        productDto.setRamInGB(0);
        productDto.setOs("N/A");
        productDto.setStorageCapacityGB(0);
        productDto.setWarrantyYears(1);
        productDto.setIncludesBatteryWarranty(false);
        productDto.setIncludesAdapterWarranty(true);
        productDto.setTouchscreen(false);
        productDto.setHasWebcam(false);
        productDto.setProductCondition("New");
        productDto.setImageUrl("http://example.com/keyboardpro.jpg");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setProductName("Keyboard Pro");
        savedProduct.setPrice(75);
        savedProduct.setProcessor("N/A");
        savedProduct.setScreenSizeInInch(0.0);
        savedProduct.setRamInGB(0);
        savedProduct.setOs("N/A");
        savedProduct.setStorageCapacityGB(0);
        savedProduct.setWarrantyYears(1);
        savedProduct.setIncludesBatteryWarranty(false);
        savedProduct.setIncludesAdapterWarranty(true);
        savedProduct.setTouchscreen(false);
        savedProduct.setHasWebcam(false);
        savedProduct.setProductCondition("New");
        savedProduct.setImageUrl("http://example.com/keyboardpro.jpg");

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.saveProduct(productDto);

        assertEquals("Keyboard Pro", result.getProductName());
        assertEquals(75, result.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSearchProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setProductName("Laptop");
        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("Gaming Laptop");
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findByProductNameContainingIgnoreCase("laptop")).thenReturn(products);

        List<Product> result = productService.searchProducts("laptop");

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getProductName());
        verify(productRepository, times(1)).findByProductNameContainingIgnoreCase("laptop");
    }
}