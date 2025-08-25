package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.Dto.ProductDto;
import com.dipComputer.Dip.Computer.model.Product;
import com.dipComputer.Dip.Computer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
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
    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository
                .findByProductNameContainingIgnoreCaseOrProcessorContainingIgnoreCaseOrRamContainingIgnoreCaseOrStorageContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        keyword, keyword, keyword, keyword, keyword, pageable);
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

        String imageUrl = productDto.getProductImageUrl();
        if (imageUrl != null && imageUrl.contains("drive.google.com")) {
            String fileId = extractFileIdFromGoogleDriveUrl(imageUrl);
            if (fileId != null) {
                product.setProductImageUrl("api/products/image/" + fileId);
            } else {
                product.setProductImageUrl(imageUrl);
            }
        } else {
            product.setProductImageUrl(imageUrl);
        }
    }

    private String extractFileIdFromGoogleDriveUrl(String url) {
        String fileId = null;
        if (url != null) {
            if (url.contains("uc?export=view&id=")) {
                int startIndex = url.indexOf("id=");
                if (startIndex != -1) {
                    fileId = url.substring(startIndex + 3);
                }
            } else {
                int startIndex = url.indexOf("/d/");
                if (startIndex != -1) {
                    int endIndex = url.indexOf("/", startIndex + 3);
                    if (endIndex != -1) {
                        fileId = url.substring(startIndex + 3, endIndex);
                    } else {
                        fileId = url.substring(startIndex + 3);
                    }
                }
            }
        }
        // clean file id
        if (fileId != null) {
            int lastIndex = fileId.indexOf("?");
            if (lastIndex != -1) {
                fileId = fileId.substring(0, lastIndex);
            }
        }
        return fileId;
    }

    @Override
    public List<String> getSearchSuggestions(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword).stream().map(Product::getProductName)
                .toList();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByProductName(name);
    }
}
