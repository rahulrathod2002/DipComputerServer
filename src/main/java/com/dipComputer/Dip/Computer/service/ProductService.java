package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.Dto.ProductDto;
import com.dipComputer.Dip.Computer.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable);
    Product getProductById(Long id);
    Product saveProduct(ProductDto productDto);
    void deleteProduct(Long id);
    Page<Product> searchProducts(String keyword, Pageable pageable);
    Product updateProduct(Long id, ProductDto productDto);
    List<String> getSearchSuggestions(String keyword);
    Product getProductByName(String name);
}
