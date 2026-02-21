package com.narin.ecommerce.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponse {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Long categoryId;
}
