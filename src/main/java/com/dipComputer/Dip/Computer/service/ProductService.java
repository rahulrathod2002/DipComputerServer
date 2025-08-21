package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.Dto.ProductDto;
import com.dipComputer.Dip.Computer.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(ProductDto productDto);
    void deleteProduct(Long id);
    List<Product> searchProducts(String keyword);
    Product updateProduct(Long id, ProductDto productDto);
    List<String> getSearchSuggestions(String keyword);
    Product getProductByName(String name);
}
