package com.example.pizzabackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Document(collection="pizzas")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Pizza {

    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private String name;

    private Date createdAt = new Date();

    public Pizza(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(id, pizza.id) &&
                Objects.equals(name, pizza.name) &&
                Objects.equals(createdAt, pizza.createdAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, createdAt);
    }
}
