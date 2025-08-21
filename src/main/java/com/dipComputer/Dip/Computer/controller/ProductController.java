package com.dipComputer.Dip.Computer.controller;

import com.dipComputer.Dip.Computer.Dto.ProductDto;
import com.dipComputer.Dip.Computer.model.Product;
import com.dipComputer.Dip.Computer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return productService.searchProducts(search);
        } else {
            return productService.getAllProducts();
        }
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/suggestions")
    public List<String> getSearchSuggestions(@RequestParam String keyword) {
        return productService.getSearchSuggestions(keyword);
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("/image/{fileId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileId) throws IOException {
        String url = "https://drive.google.com/uc?export=view&id=" + fileId;
        java.net.URL imageUrl = new java.net.URL(url);

        // Open connection to Google Drive link
        URLConnection connection = imageUrl.openConnection();
        String contentType = connection.getContentType(); // auto-detects (image/jpeg, image/png, etc.)

        try (InputStream in = connection.getInputStream()) {
            byte[] imageBytes = in.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
                    headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setPragma("no-cache");
        headers.setExpires(0L);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }
    }

}