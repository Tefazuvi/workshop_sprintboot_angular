package com.example.pizzabackend.dto;

import java.util.Objects;

public class PizzaDto {

    private String id;
    private String name;
    private int price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PizzaDto(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaDto pizzaDto = (PizzaDto) o;
        return price == pizzaDto.price &&
                Objects.equals(id, pizzaDto.id) &&
                Objects.equals(name, pizzaDto.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price);
    }
}
