package org.example;

import jakarta.persistence.*;//Import libraries

@Entity//Define entity
@Table(name="movie")//Define table name
public class Movie {//Movie class

    @Id//Define primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto-generate ID
    private int id;

    private String title;
    private int releaseYear;
    private String genre;

    @ManyToOne//Many-to-one relationship with Director
    @JoinColumn(name="director_id")//Join column
    private Director director;

    public int getId() {//Getter and setter methods
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
