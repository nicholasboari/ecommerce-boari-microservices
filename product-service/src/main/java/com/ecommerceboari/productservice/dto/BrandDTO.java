package com.ecommerceboari.productservice.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandDTO {

    private Long id;

    @Size(min = 2, message = "size needs to be greater than 2")
    private String name;
}