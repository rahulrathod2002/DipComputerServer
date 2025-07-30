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
        if (keyword.startsWith("under ")) {
            try {
                double price = Double.parseDouble(keyword.substring(6));
                return productRepository.findByPriceLessThan(price);
            } catch (NumberFormatException e) {
                // Ignore if the number is not valid
            }
        } else if (keyword.startsWith("above ")) {
            try {
                double price = Double.parseDouble(keyword.substring(6));
                return productRepository.findByPriceGreaterThan(price);
            } catch (NumberFormatException e) {
                // Ignore if the number is not valid
            }
        }
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
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
        product.setScreenSizeInInch(productDto.getScreenSizeInInch());
        product.setRamInGB(productDto.getRamInGB());
        product.setOs(productDto.getOs());
        product.setStorageCapacityGB(productDto.getStorageCapacityGB());
        product.setWarrantyYears(productDto.getWarrantyYears());
        product.setIncludesBatteryWarranty(productDto.isIncludesBatteryWarranty());
        product.setIncludesAdapterWarranty(productDto.isIncludesAdapterWarranty());
        product.setTouchscreen(productDto.isTouchscreen());
        product.setHasWebcam(productDto.isHasWebcam());
        product.setProductCondition(productDto.getProductCondition());
        product.setImageUrl(productDto.getImageUrl());
    }

    @Override
    public List<String> getSearchSuggestions(String keyword) {
        return productRepository.findTop5ByProductNameContainingIgnoreCase(keyword);
    }
}
