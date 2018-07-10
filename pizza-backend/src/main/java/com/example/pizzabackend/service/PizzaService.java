package com.example.pizzabackend.service;

import com.example.pizzabackend.domain.Pizza;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PizzaService {

    Optional<List<Pizza>> getAllPizzas();

    void insertPizza(Pizza pizza);

    Optional<Pizza> getPizzaByName(@NotNull String name);

    Optional<List<Pizza>> getPizzasByPrice(int price);

    Optional<Pizza> updatePizza(String id, int price);

    void deletePizzaById(String id);
}
