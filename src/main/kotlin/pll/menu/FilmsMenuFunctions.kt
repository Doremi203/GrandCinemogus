package pll.menu

import di.Di
import pll.models.input.FilmData

fun showAllFilms() {
    val films = Di.filmsController.getAllFilms()

    if (films.isEmpty()) {
        println("Список фильмов пуст")
        return
    }

    println("Список фильмов:")
    films.forEach { film ->
        println("Id: ${film.id}. ${film.title}")
    }
}

fun addFilm() {
println("Введите название фильма:")
    val title = Di.inputReader.readStringUntilNotNull()

    println("Введите актеров через запятую:")
    val actors = readlnOrNull()?.split(",")?.toMutableList() ?: mutableListOf()

    val newFilmData = FilmData(title, actors)

    Di.filmsController.addFilm(newFilmData)
}

fun deleteFilm() {
    println("Введите id фильма:")
    val filmId = Di.inputReader.readIntUntilNotNull()

    val res = Di.filmsController.deleteFilm(filmId)
    println(res)
}

fun processEditFilmMenu() {
    val menu = Di.filmEditMenu
    menu.handle()
}