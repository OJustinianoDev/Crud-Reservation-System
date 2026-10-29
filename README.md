# Crud-Reservation-System

Console reservation system built with Java and a MySQL database.
Evolution of [Simple-Reservation-System](https://github.com/OJustinianoDev/Simple-Reservation-System), which stored only one reservation in memory.

## Features

- List reservations
- Cancel a reservation
- Reschedule a reservation
- Create a reservation, automatically registering the customer (by CPF) when they don't exist yet
- Invalid input handling (letters instead of numbers, wrong date format) and date validation (check-out must be after check-in)

## Technologies

- Java
- MySQL
- JDBC (MySQL Connector/J 8.4.0)

## Project Structure

| File | Responsibility |
|---|---|
| `Main` | Menu and program flow |
| `Conexao` | Database connection, reading `db.properties` |
| `Cliente` / `Reserva` | Classes that represent the data |
| `ClienteDAO` / `ReservaDAO` | SQL commands (database access) |

## Database

Create the database and the two tables in MySQL:

```sql
CREATE DATABASE sistema_reserva;
USE sistema_reserva;

CREATE TABLE cliente (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    telefone VARCHAR(17) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reserva (
    id INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    entrada DATETIME NOT NULL,
    saida DATETIME NOT NULL,
    descricao VARCHAR(100),
    tipo VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE
);
```

## How to Run

1. Copy `db.properties.example` to `db.properties` (in the project root).
2. Fill in the URL, user and password of your MySQL in `db.properties`.
3. Add the driver `com.mysql:mysql-connector-j:8.4.0` to the project.
4. Run the `Main` class.

> The `db.properties` file contains the database password and is listed in `.gitignore`: it is not sent to GitHub.

## Author

Cauã Justiniano