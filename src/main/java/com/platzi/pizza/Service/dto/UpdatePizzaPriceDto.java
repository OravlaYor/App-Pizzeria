package com.platzi.pizza.Service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdatePizzaPriceDto {
    private int pizzaId;
    private BigDecimal newPrice;


}
