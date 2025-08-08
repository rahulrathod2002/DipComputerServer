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
        product1.setPrice("1200");
        product1.setProcessor("Intel i7");
        product1.setRam("16");
        product1.setStorage("512");
        product1.setWarranty("2");
        product1.setDescription("A powerful laptop");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("Mouse Pro");
        product2.setPrice("25");
        product2.setProcessor("N/A");
        product2.setRam("N/A");
        product2.setStorage("N/A");
        product2.setWarranty("1");
        product2.setDescription("A wireless mouse");

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Laptop X1", result.get(0).getProductName());
        assertEquals("1200", result.get(0).getPrice());
        assertEquals("Intel i7", result.get(0).getProcessor());
        assertEquals("16", result.get(0).getRam());
        assertEquals("512", result.get(0).getStorage());
        assertEquals("2", result.get(0).getWarranty());
        assertEquals("A powerful laptop", result.get(0).getDescription());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductByIdFound() {
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Laptop X1");
        product.setPrice("1200");
        product.setProcessor("Intel i7");
        product.setRam("16");
        product.setStorage("512");
        product.setWarranty("2");
        product.setDescription("A powerful laptop");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals("Laptop X1", result.getProductName());
        assertEquals("1200", result.getPrice());
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
        productDto.setPrice("75");
        productDto.setProcessor("N/A");
        productDto.setRam("N/A");
        productDto.setStorage("N/A");
        productDto.setWarranty("1");
        productDto.setDescription("A wireless keyboard");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setProductName("Keyboard Pro");
        savedProduct.setPrice("75");
        savedProduct.setProcessor("N/A");
        savedProduct.setRam("N/A");
        savedProduct.setStorage("N/A");
        savedProduct.setWarranty("1");
        savedProduct.setDescription("A wireless keyboard");

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.saveProduct(productDto);

        assertEquals("Keyboard Pro", result.getProductName());
        assertEquals("75", result.getPrice());
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

        when(productRepository.searchByKeyword("laptop")).thenReturn(products);

        List<Product> result = productService.searchProducts("laptop");

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getProductName());
        verify(productRepository, times(1)).searchByKeyword("laptop");
    }
}