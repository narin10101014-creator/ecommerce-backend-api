package com.narin.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    @NotBlank
    private String name;

    private String description;
    @NotNull
    private Double price;

    @NotNull
    private Integer stock;

    @NotNull
    private Long categoryId;
}
