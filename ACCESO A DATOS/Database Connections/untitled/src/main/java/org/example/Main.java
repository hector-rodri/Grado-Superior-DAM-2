package org.example;

import org.hibernate.*;//Import libraries
import java.util.*;

public class Main {//Main class
    private static Scanner sc = new Scanner(System.in);//Scanner for user input

    public static void main(String[] args) {//Main method
        while (true) {//Main menu loop
            HibernateUtil.buildSessionFactory();
            System.out.println("CINEMA DATABASE");//Menu options
            System.out.println("1 - Menu Directors");
            System.out.println("2 - Menu Movies");
            System.out.println("3 - Exit");
            System.out.print("Option: ");
            int op = sc.nextInt();//Read user option

            switch (op) {//Handle menu options
                case 1:
                    menuDirectors();//Directors menu
                    break;
                case 2:
                    menuMovies();//Movies menu
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void menuDirectors() {//Directors menu
        while (true) {//Directors menu loop
            System.out.println("DIRECTORS");//Menu options
            System.out.println("1 - Create Director");
            System.out.println("2 - Show Directors");
            System.out.println("3 - Update Director");
            System.out.println("4 - Delete Director");
            System.out.println("5 - Back");
            System.out.print("Option: ");
            int op = sc.nextInt();//Read user option

            switch (op) {//Handle menu options
                case 1:
                    createDirector();//Create director
                    break;
                case 2:
                    showDirectors();//Show directors
                    break;
                case 3:
                    updateDirector();//Update director
                    break;
                case 4:
                    deleteDirector();//Delete director
                    break;
                case 5:
                    return;
            }
        }
    }

    public static void menuMovies() {//Movies menu
        while (true) {//Movies menu loop
            System.out.println("MOVIES");//Menu options
            System.out.println("1 - Create Movie");
            System.out.println("2 - Show Movies");
            System.out.println("3 - Update Movie");
            System.out.println("4 - Delete Movie");
            System.out.println("5 - Back");
            System.out.print("Option: ");
            int op = sc.nextInt();//Read user option

            switch (op) {//Handle menu options
                case 1:
                    createMovie();//Create movie
                    break;
                case 2:
                    showMovies();//Show movies
                    break;
                case 3:
                    updateMovie();//Update movie
                    break;
                case 4:
                    deleteMovie();//Delete movie
                    break;
                case 5:
                    return;
            }
        }
    }

    public static void createDirector() {//Create director
        Session session = HibernateUtil.getSessionFactory().openSession();//Open Hibernate session
        session.beginTransaction();//Begin transaction
        Director director = new Director();//Create new Director object
        sc.nextLine();
        System.out.print("Name: ");//Read director details
        director.setName(sc.nextLine());
        System.out.print("Nationality: ");
        director.setNationality(sc.nextLine());
        session.persist(director);//Add director to session
        session.getTransaction().commit();//Commit transaction
        session.close();//Close session
        System.out.println("Director created");
    }

    public static void showDirectors() {//Show directors
        Session session = HibernateUtil.getSessionFactory().openSession();//Open Hibernate session
        List<Director> list = session.createQuery("from Director", Director.class).list();//Query all directors
        System.out.println("Directors");
        if (list.isEmpty()) {//Check if list is empty
            System.out.println("Not found");
            session.close();//Close session
            return;
        }
        for (Director director : list) {//Display director details
            System.out.println("ID: " + director.getId());
            System.out.println("Name: " + director.getName());
            System.out.println("Nationality: " + director.getNationality());
        }
        session.close();//Close session
    }

    public static void updateDirector() {//Update director
        Session session = HibernateUtil.getSessionFactory().openSession();//Open Hibernate session
        session.beginTransaction();//Begin transaction
        System.out.print("Director ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Director director = session.get(Director.class, id);//Get director by ID
        if (director == null) {//Check if director exists
            System.out.println("Director not found");
            session.close();//Close session
            return;
        }
        System.out.print("New name: ");//Read new details
        director.setName(sc.nextLine());
        System.out.print("New nationality: ");
        director.setNationality(sc.nextLine());
        session.getTransaction().commit();//Commit transaction
        session.close();//Close session
        System.out.println("Director updated");
    }

    public static void deleteDirector() {//Delete director
        Session session = HibernateUtil.getSessionFactory().openSession();//Open Hibernate session
        session.beginTransaction();//Begin transaction
        System.out.print("Director ID to delete: ");
        int id = sc.nextInt();
        Director director = session.get(Director.class, id);//Get director by ID
        if (director != null) {//Check if director exists
            session.remove(director);//Delete director (cascade deletes movies)
            System.out.println("Director and all movies deleted");
        } else {
            System.out.println("Director not found");
        }
        session.getTransaction().commit();//Commit transaction
        session.close();//Close session
    }

    public static void createMovie() {//Create movie
        Session session = HibernateUtil.getSessionFactory().openSession();//Open Hibernate session
        session.beginTransaction();//Begin transaction
        System.out.print("Director ID for the movie: ");
        int directorId = sc.nextInt();
        sc.nextLine();
        Director director = session.get(Director.class, directorId);//Get director by ID
        if (director == null) {//Check if director exists
            System.out.println("Director not found");
            session.close();//Close session
            return;
        }
        Movie movie = new Movie();//Create new Movie object
        System.out.print("Movie title: ");//Read movie details
        movie.setTitle(sc.nextLine());
        System.out.print("Release year: ");
        movie.setReleaseYear(sc.nextInt());
        sc.nextLine();
        System.out.print("Genre: ");
        movie.setGenre(sc.nextLine());
        director.addMovie(movie);//Associate movie with director
        session.persist(director);//Add director and movie to session
        session.getTransaction().commit();//Commit transaction
        session.close();//Close session
        System.out.println("Movie created");
    }

    public static void showMovies() {//Show movies
        Session session = HibernateUtil.getSessionFactory().openSession();//Open Hibernate session
        List<Movie> list = session.createQuery("from Movie", Movie.class).list();//Query all movies
        System.out.println("Movies");
        if (list.isEmpty()) {//Check if list is empty
            System.out.println("Not found");
            session.close();//Close session
            return;
        }
        for (Movie movie : list) {//Display movie details
            System.out.println("ID: " + movie.getId());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Release Year: " + movie.getReleaseYear());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Director: " + (movie.getDirector() != null ? movie.getDirector().getName() : "None"));
        }
        session.close();//Close session
    }

    public static void updateMovie() {//Update movie
        Session session = HibernateUtil.getSessionFactory().openSession();//Open Hibernate session
        session.beginTransaction();//Begin transaction
        System.out.print("Movie ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Movie movie = session.get(Movie.class, id);//Get movie by ID
        if (movie == null) {//Check if movie exists
            System.out.println("Movie not");
            session.close();//Close session
            return;
        }
        System.out.print("New title: ");//Read new details
        movie.setTitle(sc.nextLine());
        System.out.print("New release year: ");
        movie.setReleaseYear(sc.nextInt());
        sc.nextLine();
        System.out.print("New genre: ");
        movie.setGenre(sc.nextLine());
        session.getTransaction().commit();//Commit transaction
        session.close();//Close session
        System.out.println("Movie updated ");
    }

    public static void deleteMovie() {//Delete movie
        Session session = HibernateUtil.getSessionFactory().openSession();//Open Hibernate session
        session.beginTransaction();//Begin transaction
        System.out.print("Movie ID to delete: ");
        int id = sc.nextInt();
        Movie movie = session.get(Movie.class, id);//Get movie by ID
        if (movie != null) {//Check if movie exists
            session.remove(movie);//Delete movie
            System.out.println("Movie deleted");
        } else {
            System.out.println("Movie not found");
        }
        session.getTransaction().commit();//Commit transaction
        session.close();//Close session
    }
}