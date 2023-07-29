package com.mateus.spring.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDto(@NotBlank(message = "The name cannot null or empty") String name,@NotNull(message = "The value cannot null") BigDecimal value){
}
