# 🍳 Cooking School Management System (ObjectDB + JPA)

This project contains a Java application that manages the data of a **Cooking School**, allowing the user to work with **chefs, courses, students, and recipes**.  
The system uses **ObjectDB** as an embedded database and provides a **console-based interactive menu** to perform CRUD operations for each entity

## Development Environment Information

- **Operating System:** Windows 11  
- **IDE used:** Visual Studio Code
- **Java version:** Java 24  
- **Database:** ObjectDB
- **Libraries used:**  
  - `javax.persistence` for JPA entity management  
  - `java.util` for lists and Scanner  
  - `objectdb.jar` (required to run ObjectDB persistence)

## How to Run the Program

1. Download or clone this repository 
2. Make sure you have objectdb.jar in your project's classpath
3. Ensure your resources\META-INF\persistence.xml includes a persistence unit named: objectdb_restaurant
4. Run Main.java.  
5. Use the console menu to navigate through the options

## Database Schema 

ObjectDB does not require SQL table creation,  
but this is the SQL-equivalent model of JPA entities:

```sql
CREATE TABLE Chef (
    id INTEGER PRIMARY KEY,
    name TEXT,
    specialty TEXT
);

CREATE TABLE Course (
    id INTEGER PRIMARY KEY,
    name TEXT,
    duration INTEGER,
    chef_id INTEGER,
    FOREIGN KEY (chef_id) REFERENCES Chef(id)
);

CREATE TABLE Recipe (
    id INTEGER PRIMARY KEY,
    name TEXT,
    difficulty TEXT,
    course_id INTEGER,
    FOREIGN KEY (course_id) REFERENCES Course(id)
);

CREATE TABLE Student (
    id INTEGER PRIMARY KEY,
    name TEXT,
    course_id INTEGER,
    FOREIGN KEY (course_id) REFERENCES Course(id)
);

