package com.ecommerceboari.productservice.controller;

import com.ecommerceboari.productservice.dto.BrandDTO;
import com.ecommerceboari.productservice.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<Page<BrandDTO>> findAllPaged(Pageable pageable) {
        Page<BrandDTO> paged = brandService.findPaged(pageable);
        LOGGER.info("Received request to fetch all brands paginated");
        return ResponseEntity.ok().body(paged);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BrandDTO>> findAll() {
        List<BrandDTO> brandList = brandService.findAll();
        LOGGER.info("Received request to fetch all brands");
        return ResponseEntity.ok().body(brandList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> findById(@PathVariable Long id) {
        BrandDTO brand = brandService.findById(id);
        LOGGER.info("Received request to fetch brand with ID: {}", id);
        return ResponseEntity.ok().body(brand);
    }

    @GetMapping("/find")
    public ResponseEntity<List<BrandDTO>> findByName(@RequestParam String name) {
        List<BrandDTO> brand = brandService.findByNameContaining(name);
        LOGGER.info("Received request to fetch a brand by name");
        return ResponseEntity.ok().body(brand);
    }

    @PostMapping
    public ResponseEntity<BrandDTO> save(@Valid @RequestBody BrandDTO brandDTO) {
        BrandDTO brand = brandService.save(brandDTO);
        LOGGER.info("Received request to create a new brand");
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> update(@Valid @RequestBody BrandDTO brandDTO, @PathVariable Long id){
        BrandDTO brandUpdated = brandService.update(brandDTO, id);
        LOGGER.info("Received request to update a brand with ID: {}", id);
        return ResponseEntity.ok().body(brandUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BrandDTO> delete(@PathVariable Long id) {
        brandService.delete(id);
        LOGGER.info("Received request to delete a brand with ID: {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}