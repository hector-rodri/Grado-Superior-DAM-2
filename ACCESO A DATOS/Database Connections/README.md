# 🎬 Cinema Management System (Hibernate + MySQL)

This project contains a Java application that manages the data of a **Cinema**, allowing the user to work with **directors and movies**.  
The system uses **Hibernate** with **MySQL** and provides a **console-based interactive menu** to perform CRUD operations for each entity.

## Development Environment Information

- **Operating System:** Windows / Linux / macOS  
- **IDE used:** IntelliJ IDEA  
- **Java version:** Java 23  
- **Database:** MySQL  
- **Libraries used:**  
  - `jakarta.persistence` for JPA entity management  
  - `org.hibernate` for ORM (Hibernate Core 6.x)  
  - `com.mysql:mysql-connector-j` for MySQL connection  
  - `org.slf4j` for logging  
  - `java.util` for lists and Scanner  


### How the Project Was Developed

1. **Entities annotated with JPA**
   - Used `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@OneToMany`, and `@ManyToOne` to map the Director and Movie tables.
   - Cascade and orphan removal (`cascade = CascadeType.ALL`, `orphanRemoval = true`) ensure that deleting a director also deletes all associated movies.

2. **Hibernate Configuration**
   - Created a `HibernateUtil` class that:
     - Reads database connection information from an external file (`bbdd_connection.txt`).
     - Configures Hibernate programmatically (or optionally via `hibernate.cfg.xml`).
     - Builds the `SessionFactory`.
   - Hibernate automatically creates the tables if they do not exist (`hbm2ddl.auto=update`).

3. **Console-based CRUD**
   - An interactive console menu using `Scanner` allows users to:
     - Create, read, update, and delete directors and movies.
     - List movies for each director and display the director of each movie.
   - Separate menus for **Directors** and **Movies** for clarity.

4. **External Database**
   - The database connection is dynamically loaded from `bbdd_connection.txt`.
   - You can switch environments without changing the code.
   - MySQL will automatically create the database and tables if they don’t exist.


## How to Use the Program

1. **Setup**
   - Clone or download the repository.
   - Create a file named `bbdd_connection.txt` in the root of the project.

2. **Run the Program**
   - Open the project in **IntelliJ IDEA** (or your preferred IDE).
   - Ensure all dependencies are included (Hibernate, MySQL connector, SLF4J).
   - Run `Main.java`.
   - Use the console menu to manage **Directors** and **Movies**.

## Database Schema

The tables are created automatically by Hibernate.

```sql
CREATE TABLE Director (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    nationality VARCHAR(255),
);

CREATE TABLE Movie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    releaseYear INT,
    genre VARCHAR(255),
    director_id INT,
    FOREIGN KEY (director_id) REFERENCES Director(id)
);