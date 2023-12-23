package pll.menu

import dal.entities.FilmAddEntity
import di.Di
import pll.exceptions.NoFilmsException
import pll.models.output.FilmOutput

fun showAllFilms() {
    val films = Di.filmsRepository.getAll().map { FilmOutput(it.title, it.actors) }

    if (films.isEmpty()) {
        throw NoFilmsException("Список фильмов пуст")
    }

    println("Список фильмов:")
    films.forEachIndexed { id, film ->
        println("${id}. ${film.title}")
    }
}

fun addFilm() {
    println("Введите название фильма:")
    val title = Di.inputReader.readStringUntilNotNull()

    println("Введите актеров через запятую:")
    val actors = readlnOrNull()?.split(",")?.toMutableList() ?: mutableListOf()

    Di.filmsRepository.add(FilmAddEntity(title, actors))

    println("Фильм: $title успешно добавлен")
}

fun deleteFilm() {
    println("Введите название фильма для удаления:")
    val title = Di.inputReader.readStringUntilNotNull()

    Di.filmsRepository.delete(Di.filmIdService.getFilmIdFromTitle(title))

    println("Фильм: $title успешно удален")
}

fun processEditFilmMenu() {
    val menu = Di.filmEditMenu
    menu.handle()
}