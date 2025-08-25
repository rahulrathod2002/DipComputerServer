package com.dipComputer.Dip.Computer.repository;

import com.dipComputer.Dip.Computer.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String keyword);

    Product findByProductName(String productName);

    Page<Product> findByProductNameContainingIgnoreCaseOrProcessorContainingIgnoreCaseOrRamContainingIgnoreCaseOrStorageContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String productName, String processor, String ram, String storage, String description, Pageable pageable);
}
