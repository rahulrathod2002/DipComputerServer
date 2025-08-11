package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.Dto.ProductDto;
import com.dipComputer.Dip.Computer.model.Product;
import com.dipComputer.Dip.Computer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(ProductDto productDto) {
        Product product = new Product();
        updateProductFromDto(product, productDto);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.searchByKeyword(keyword);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = getProductById(id);
        if (product != null) {
            updateProductFromDto(product, productDto);
            return productRepository.save(product);
        }
        return null;
    }

    private void updateProductFromDto(Product product, ProductDto productDto) {
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setProcessor(productDto.getProcessor());
        product.setRam(productDto.getRam());
        product.setStorage(productDto.getStorage());
        product.setWarranty(productDto.getWarranty());
        product.setScreenSizeInInch(productDto.getScreenSizeInInch());
        product.setDescription(productDto.getDescription());
        product.setProductImageUrl(productDto.getProductImageUrl());
    }

    @Override
    public List<String> getSearchSuggestions(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword).stream().map(Product::getProductName).toList();
    }
}
