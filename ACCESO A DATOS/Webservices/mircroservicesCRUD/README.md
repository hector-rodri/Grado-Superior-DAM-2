# Microservices CRUD - Products API

This project contains a **Spring Boot REST API** that manages a **products catalogue**,
allowing users to perform CRUD operations via HTTP requests.  
The application uses **SQLite** as a local database and exposes a JSON REST API.

## Development Environment Information

- **Operating System:** Windows 11
- **IDE used:** IntelliJ IDEA
- **Java version:** Java 21
- **Framework:** Spring Boot 4.0.2
- **Database:** SQLite
- **Libraries used:**
  - `spring-boot-starter-web` for REST API
  - `spring-boot-starter-data-jpa` for database access
  - `sqlite-jdbc` for SQLite driver
  - `hibernate-community-dialects` for SQLite Hibernate dialect
  - `spring-boot-devtools` for hot reload during development

## How to Run the Program

1. Clone or download this repository
2. Make sure you have **Java 21** and **Maven** installed
3. Start the application
4. The API will be available at: `http://localhost:8080`

The SQLite database file (productes.db) will be created automatically on first run.

## API Endpoints

| Method | URL                | Description              | Success Code |
|--------|--------------------|--------------------------|--------------|
| POST   | /productes         | Create a new product     | 201          |
| GET    | /productes         | List all products        | 200          |
| GET    | /productes/{id}    | Get a product by ID      | 200          |
| PUT    | /productes/{id}    | Update an existing product | 200        |
| DELETE | /productes/{id}    | Delete a product         | 204          |

## Example JSON 
```json
{
  "name": "Laptop Dell XPS",
  "description": "High-end laptop with OLED screen",
  "price": 1299.99,
  "quantity": 10
}
```

## Database Schema
```sql
CREATE TABLE productes (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    name     TEXT    NOT NULL,
    description TEXT,
    price    REAL    NOT NULL,
    quantity INTEGER NOT NULL
);
```