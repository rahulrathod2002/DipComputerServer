package com.dipComputer.Dip.Computer.repository;

import com.dipComputer.Dip.Computer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String keyword);

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(p.price AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.processor) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(p.ramInGB AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(p.storageCapacityGB AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(p.screenSizeInInch AS string) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE p.price < :price")
    List<Product> findByPriceLessThan(@Param("price") double price);

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findByPriceGreaterThan(@Param("price") double price);

    @Query("SELECT p.productName FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<String> findTop5ByProductNameContainingIgnoreCase(@Param("keyword") String keyword);
}