package com.example;


import jakarta.persistence.*;

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String puesto;

    public Empleado() {}

    public Empleado(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
    }

    public Long getId() { return id; }

    public void setId(Long id) {    this.id = id;   }

    public String getNombre() { return nombre;  }
    public void setNombre(String nombre)    {   this.nombre = nombre; }

    public String getPuesto()   { return puesto;  }
    public void setPuesto(String puesto)   {   this.puesto = puesto;  }
    
}
