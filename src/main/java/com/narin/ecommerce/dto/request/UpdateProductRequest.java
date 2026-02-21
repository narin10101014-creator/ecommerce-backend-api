package com.narin.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private Double price;

    @NotBlank
    private Integer stock;

    @NotBlank
    private Long categoryId;
}
