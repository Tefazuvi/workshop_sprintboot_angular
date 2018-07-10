package com.example.pizzabackend.repository;

import com.example.pizzabackend.domain.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface PizzaRepository extends MongoRepository<Pizza, String> {

    Optional<List<Pizza>> findByPrice(int price);

    Optional<Pizza> findByName(String name);
}
