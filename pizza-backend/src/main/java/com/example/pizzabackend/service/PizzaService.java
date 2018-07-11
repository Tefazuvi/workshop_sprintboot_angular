package com.example.pizzabackend.service;

import com.example.pizzabackend.domain.Pizza;
import com.example.pizzabackend.dto.PizzaDto;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PizzaService {

    Optional<List<PizzaDto>> getAllPizzas();

    void insertPizza(Pizza pizza);

    Optional<PizzaDto> getPizzaByName(@NotNull String name);

    Optional<List<PizzaDto>> getPizzasByPrice(int price);

    Optional<PizzaDto> updatePizza(String id, int price);

    void deletePizzaById(String id);
}
