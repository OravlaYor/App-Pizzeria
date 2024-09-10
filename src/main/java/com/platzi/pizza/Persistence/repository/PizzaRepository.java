package com.platzi.pizza.Persistence.repository;

import com.platzi.pizza.Persistence.entity.PizzaEntity;
import com.platzi.pizza.Service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity ,Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByPriceGreaterThan(BigDecimal price);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(BigDecimal price);
    List<PizzaEntity> findAllByPriceLessThan(BigDecimal price);
    int countByVeganTrue();

    /*@Query(value =
            "UPDATE pizza" +
                    "SET price = :newPrice " +
                    "WHERE id_pizza = :idPizza",nativeQuery = true)
    void updatePrice1(@Param("idPizza") int idPizza, BigDecimal newPrice);*/

    //other way to use param inside consults @Query
    @Query(value = "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);




}
