package com.example.pizzabackend.service.impl;

import com.example.pizzabackend.domain.Pizza;
import com.example.pizzabackend.repository.PizzaRepository;
import com.example.pizzabackend.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public Optional<List<Pizza>> getAllPizzas() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        if (CollectionUtils.isEmpty(pizzas)) {
            return Optional.empty();
        } else {
            return Optional.of(pizzas);
        }

    }

    @Override
    public void insertPizza(Pizza pizza) {
        pizzaRepository.insert(pizza);
    }

    @Override
    public Optional<Pizza> getPizzaByName(@NotNull String name) {
        Optional pizza = pizzaRepository.findByName(name);
        return pizza.isPresent() ? pizza : Optional.empty();
    }

    @Override
    public Optional<List<Pizza>> getPizzasByPrice(int price) {
        Optional pizzas = pizzaRepository.findByPrice(price);
        return pizzas.isPresent() ? pizzas : Optional.empty();
    }

    @Override
    public Optional<Pizza> updatePizza(String id, int price) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);

        if (pizza.isPresent()) {
            pizza.get().setPrice(price);
            return Optional.of(pizzaRepository.save(pizza.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deletePizzaById(String id) {
        pizzaRepository.deleteById(id);
    }
}
