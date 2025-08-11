package com.dipComputer.Dip.Computer.repository;

import com.dipComputer.Dip.Computer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String keyword);

    @Query("SELECT p FROM Product p WHERE " +
            "p.productName LIKE CONCAT('%', :keyword, '%') OR " +
            "p.processor LIKE CONCAT('%', :keyword, '%') OR " +
            "p.ram LIKE CONCAT('%', :keyword, '%') OR " +
            "p.storage LIKE CONCAT('%', :keyword, '%') OR " +
            "p.description LIKE CONCAT('%', :keyword, '%')")
    List<Product> searchByKeyword(@Param("keyword") String keyword);
}