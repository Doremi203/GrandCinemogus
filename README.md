# GRAND CINEMOGUS

This application is a cinema management system written in Kotlin and Java, using Gradle as a build tool. It allows users to manage films, sessions, and tickets.

## Features

- User registration and login
- Film management: add, delete, and edit films
- Session management: add, delete, and view sessions for a specific film
- Ticket management: buy, return, and tag visitors
- View hall layout for a specific session

## Running the Application

Run the `main` function located in the `Main.kt` file.

## Built With

- [Kotlin](https://kotlinlang.org/)
- [Java](https://www.java.com/)
- [Gradle](https://gradle.org/)

## Authors

- Doremi203

The main functions in program are distributed across several files.

1. `Main.kt`: This file contains the `main` function which is the entry point of your application. It starts the registration process and then runs the application.

2. `RegistrationConsoleMenu.kt`: This file contains the functionality for user registration and login. It interacts with the user to get their login and password, validates the input, and then calls the appropriate service to register or login the user.

3. `MainConsoleMenu.kt`: This file is responsible for displaying the main menu of the application. It allows the user to navigate to the films menu or the sessions menu for a specific film.

4. `FilmsConsoleMenu.kt`: This file handles the film management part of the application. It allows the user to view all films, add a new film, delete a film, or edit a film.

5. `FilmEditConsoleMenu.kt`: This file provides the functionality to edit a film's details. It allows the user to edit the title, actors, or duration of a film.

6. `SessionsConsoleMenu.kt`: This file manages the sessions for a specific film. It allows the user to view all sessions, add a new session, delete a session, buy tickets, return tickets, tag visitors, and view the hall layout for a session.

Each of these files contains a class that extends `ConsoleMenu`, which provides a common structure for displaying a menu and processing user input. The specific actions for each menu item are defined in the derived classes.

`Di.kt` is a Dependency Injection (DI) object, here is a list of its fields and their purposes:

1. `configReader`: Reads the configuration file located at `src/main/config/config.json`.

2. `filmsRepository`, `sessionsRepository`, `cinemaRepository`, `usersRepository`: These are repositories responsible for handling data related to films, sessions, cinema, and users respectively. They use JSON files as their data sources.

3. `sessionsController`, `filmsController`, `ticketsController`: These are controllers that handle the business logic related to sessions, films, and tickets respectively.

4. `registrationService`, `ticketsService`, `filmIdService`: These are services that provide specific functionalities such as user registration, ticket management, and film ID management.

5. `filmValidator`, `registrationValidator`: These are validators that ensure the validity of film and registration data.

6. `inputReader`: This is an instance of `ConsoleInputReader` which is responsible for reading user input from the console.

7. `registrationMenu`, `mainMenu`, `filmsMenu`, `filmEditMenu`: These are instances of various console menus that provide user interfaces for different parts of the application.

P.S. Диаграмму классов пришлось автоматически сгенерировать, так как времени на создание её вручную не хватило, слишком большой проект получился:)
Надеюсь получилось сделать SOLIDный проект.