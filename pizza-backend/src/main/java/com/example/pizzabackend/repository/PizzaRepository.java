package com.example.pizzabackend.repository;

import com.example.pizzabackend.domain.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaRepository extends MongoRepository<Pizza, String> {

}
