package com.platzi.pizza.Persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    BigDecimal getOrderTotal();
    String getPizzaNames();
}
