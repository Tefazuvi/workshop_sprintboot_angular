package com.example.pizzabackend.service.impl;

import com.example.pizzabackend.domain.Pizza;
import com.example.pizzabackend.dto.PizzaDto;
import com.example.pizzabackend.repository.PizzaRepository;
import com.example.pizzabackend.service.PizzaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Type PIZZA_DTO_LIST_TYPE = new TypeToken<List<PizzaDto>>() {
    }.getType();

    @Override
    public Optional<List<PizzaDto>> getAllPizzas() {

        List<PizzaDto> pizzas = modelMapper.map(pizzaRepository.findAll(), PIZZA_DTO_LIST_TYPE);
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
    public Optional<PizzaDto> getPizzaByName(@NotNull String name) {
        Optional pizza = pizzaRepository.findByName(name);
        return pizza.isPresent() ? Optional.of(modelMapper.map(pizza.get(), PizzaDto.class)) : Optional.empty();
    }

    @Override
    public Optional<List<PizzaDto>> getPizzasByPrice(int price) {
        Optional pizzas = pizzaRepository.findByPrice(price);
        return pizzas.isPresent() ? Optional.of(modelMapper.map(pizzas.get(), PIZZA_DTO_LIST_TYPE)) : Optional.empty();
    }

    @Override
    public Optional<PizzaDto> updatePizza(@NotNull String id, int price) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);

        if (pizza.isPresent()) {
            pizza.get().setPrice(price);
            return Optional.of(modelMapper.map(pizzaRepository.save(pizza.get()),PizzaDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deletePizzaById(@NotNull String id) {
        pizzaRepository.deleteById(id);
    }
}
