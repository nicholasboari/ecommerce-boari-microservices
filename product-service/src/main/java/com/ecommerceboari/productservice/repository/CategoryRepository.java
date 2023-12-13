package com.ecommerceboari.productservice.repository;

import com.ecommerceboari.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContaining(String name);

    List<Category> findByName(String name);
}
