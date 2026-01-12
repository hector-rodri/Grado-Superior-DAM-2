package org.example;

import org.hibernate.*;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("CINEMA DATABASE");
            System.out.println("1 - Menu Directors");
            System.out.println("2 - Menu Movies");
            System.out.println("3 - Exit");
            System.out.print("Option: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    menuDirectors();
                    break;
                case 2:
                    menuMovies();
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void menuDirectors() {
        while (true) {
            System.out.println("DIRECTORS");
            System.out.println("1 - Create Director");
            System.out.println("2 - Show Directors");
            System.out.println("3 - Update Director");
            System.out.println("4 - Delete Director");
            System.out.println("5 - Back");
            System.out.print("Option: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    createDirector();
                    break;
                case 2:
                    showDirectors();
                    break;
                case 3:
                    updateDirector();
                    break;
                case 4:
                    deleteDirector();
                    break;
                case 5:
                    return;
            }
        }
    }

    public static void menuMovies() {
        while (true) {
            System.out.println("MOVIES");
            System.out.println("1 - Create Movie");
            System.out.println("2 - Show Movies");
            System.out.println("3 - Update Movie");
            System.out.println("4 - Delete Movie");
            System.out.println("5 - Back");
            System.out.print("Option: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    createMovie();
                    break;
                case 2:
                    showMovies();
                    break;
                case 3:
                    updateMovie();
                    break;
                case 4:
                    deleteMovie();
                    break;
                case 5:
                    return;
            }
        }
    }

    public static void createDirector() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Director director = new Director();
        sc.nextLine();
        System.out.print("Name: ");
        director.setName(sc.nextLine());
        System.out.print("Nationality: ");
        director.setNationality(sc.nextLine());
        session.persist(director);
        session.close();
        System.out.println("Director created");
    }

    public static void showDirectors() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Director> list = session.createQuery("from Director", Director.class).list();
        System.out.println("Directors");
        for (Director director : list) {
            System.out.println("ID: " + director.getId());
            System.out.println("Name: " + director.getName());
            System.out.println("Nationality: " + director.getNationality());
            System.out.println("Movies: ");
            for (Movie movie : director.getMovies()) {
                System.out.println("   - " + movie.getTitle() + " (" + movie.getReleaseYear() + ")");
            }
        }
        session.close();
    }

    public static void updateDirector() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.print("Director ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Director director = session.get(Director.class, id);
        if (director == null) {
            System.out.println("Director not found");
            session.close();
            return;
        }
        System.out.print("New name: ");
        director.setName(sc.nextLine());
        System.out.print("New nationality: ");
        director.setNationality(sc.nextLine());
        session.close();
        System.out.println("Director updated");
    }

    public static void deleteDirector() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.print("Director ID to delete: ");
        int id = sc.nextInt();
        Director director = session.get(Director.class, id);
        if (director != null) {
            session.remove(director);
            System.out.println("Director and all movies deleted");
        } else {
            System.out.println("Director not found");
        }
        session.close();
    }

    public static void createMovie() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.print("Director ID for the movie: ");
        int directorId = sc.nextInt();
        sc.nextLine();
        Director director = session.get(Director.class, directorId);
        if (director == null) {
            System.out.println("Director not found");
            session.close();
            return;
        }
        Movie movie = new Movie();
        System.out.print("Movie title: ");
        movie.setTitle(sc.nextLine());
        System.out.print("Release year: ");
        movie.setReleaseYear(sc.nextInt());
        sc.nextLine();
        System.out.print("Genre: ");
        movie.setGenre(sc.nextLine());
        director.addMovie(movie);
        session.persist(director);
        session.close();
        System.out.println("Movie created");
    }

    public static void showMovies() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Movie> list = session.createQuery("from Movie", Movie.class).list();
        System.out.println("Movies");
        for (Movie movie : list) {
            System.out.println("ID: " + movie.getId());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Release Year: " + movie.getReleaseYear());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Director: " + (movie.getDirector() != null ? movie.getDirector().getName() : "None"));
        }
        session.close();
    }

    public static void updateMovie() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.print("Movie ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Movie movie = session.get(Movie.class, id);
        if (movie == null) {
            System.out.println("Movie not");
            session.close();
            return;
        }
        System.out.print("New title: ");
        movie.setTitle(sc.nextLine());
        System.out.print("New release year: ");
        movie.setReleaseYear(sc.nextInt());
        sc.nextLine();
        System.out.print("New genre: ");
        movie.setGenre(sc.nextLine());
        session.close();
        System.out.println("Movie updated ");
    }

    public static void deleteMovie() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        System.out.print("Movie ID to delete: ");
        int id = sc.nextInt();
        Movie movie = session.get(Movie.class, id);
        if (movie != null) {
            session.remove(movie);
            System.out.println("Movie deleted");
        } else {
            System.out.println("Movie not found");
        }
        session.close();
    }
}