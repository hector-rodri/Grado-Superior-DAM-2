package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "productes")
public class Producte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    public Producte() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcio) {
        this.description = descripcio;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double preu) {
        this.price = preu;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantitat) {
        this.quantity = quantitat;
    }
}