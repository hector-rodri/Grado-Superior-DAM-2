package com.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Persona> empleados = new ArrayList<>();

    public Empresa() {
    }

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Persona> getEmpleados() {
        return empleados;
    }

    public void addEmpleado(Persona p) {
        empleados.add(p);
        p.setEmpresa(this);
    }

    @Override
    public String toString() {
        return "Empresa{id=" + id + ", nombre='" + nombre + "'}";
    }
}