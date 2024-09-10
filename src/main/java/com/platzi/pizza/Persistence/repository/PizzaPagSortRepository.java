package com.platzi.pizza.Persistence.repository;

import com.platzi.pizza.Persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;


public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity,Integer> {

    Page<PizzaEntity> findAllByAvailableTrue(Pageable pageable);
}
