package com.ecommerceboari.productservice.repository;

import com.ecommerceboari.productservice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByNameContaining(String name);

    List<Brand> findByName(String name);
}
