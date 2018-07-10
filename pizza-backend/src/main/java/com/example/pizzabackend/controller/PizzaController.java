package com.example.pizzabackend.controller;

import com.example.pizzabackend.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping(
            value = "/pizza",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPizzas(){
        Optional result = pizzaService.getAllPizzas();
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ResponseEntity.notFound(), HttpStatus.NOT_FOUND);
        }
    }
}
