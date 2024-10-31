package com.platzi.pizza.Persistence.repository;

import com.platzi.pizza.Persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
