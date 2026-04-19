package com.example;

import jakarta.persistence.*;//Import all libraries

@Entity//Annotation to indicate that this class is an entity
@Table(name = "productes")//Annotation to specify the table name in the database
public class Product {

    @Id//Annotation to indicate that this field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Annotation to specify that the primary key is auto-generated
    private Integer id;

    @Column(nullable = false)//Annotation to specify that this field cannot be null
    private String name;

    private String description;

    @Column(nullable = false)//Annotation to specify that this field cannot be null
    private Double price;

    @Column(nullable = false)//Annotation to specify that this field cannot be null
    private Integer quantity;

    public Product() {
    }

    public Product(String nom, String descripcio, Double preu, Integer quantitat) {//Constructor to initialize the fields
        this.name = nom;
        this.description = descripcio;
        this.price = preu;
        this.quantity = quantitat;
    }

    public Integer getId() {//Getters and setters
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