package org.example;

import jakarta.persistence.*;//Import libraries
import java.util.*;

@Entity//Define entity
@Table(name="director")//Define table name
public class Director {//Director class

    @Id//Define primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto-generate ID
    private int id;

    private String name;
    private String nationality;

    @OneToMany(mappedBy="director", cascade = CascadeType.ALL, orphanRemoval = true)//One-to-many relationship with Movie
    private List<Movie> movies = new ArrayList<>();//List of movies

    public void addMovie(Movie m){//Method to add a movie
        movies.add(m);
        m.setDirector(this);
    }

    public void removeMovie(Movie m){//Method to remove a movie
        movies.remove(m);
        m.setDirector(null);
    }

    public int getId() {//Getter and setter methods
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
