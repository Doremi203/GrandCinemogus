package pll.menu

import bll.controllers.DefaultFilmsController
import bll.controllers.interfaces.FilmsController
import bll.services.FilmIdService
import dal.entities.FilmAddEntity
import dal.repositories.interfaces.FilmsRepository
import di.Di
import pll.ConsoleInputReader
import pll.InputReader
import pll.exceptions.NoFilmsException
import pll.models.output.FilmOutput

class FilmsConsoleMenu(
    private val filmsRepository: FilmsRepository,
    private val filmsController: FilmsController,
    private val filmIdService: FilmIdService,
    private val inputReader: InputReader,
    private val filmEditMenu: ConsoleMenu
) : ConsoleMenu("Меню фильмов") {

    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Показать все фильмы", ::showAllFilms),
        MenuItem("Добавить фильм", ::addFilm),
        MenuItem("Удалить фильм", ::deleteFilm),
        MenuItem("Редактировать фильм", ::processEditFilmMenu)
    )

    fun showAllFilms() {
        val films = filmsRepository.getAll().map { FilmOutput(it.title, it.actors, it.durationInMinutes) }

        if (films.isEmpty()) {
            throw NoFilmsException("Список фильмов пуст")
        }

        println("Список фильмов:")
        films.forEachIndexed { id, film ->
            println(
                "${id + 1}. ${film.title} - " +
                        "Актеры (${film.actors.joinToString(", ")}), " +
                        "Длительность: ${film.durationInMinutes}"
            )
        }
    }

    private fun addFilm() {
        println("Введите название фильма:")
        val title = inputReader.readStringUntilNotNull()

        println("Введите актеров через запятую:")
        val actors = readlnOrNull()?.split(",")?.toMutableList() ?: mutableListOf()

        println("Введите длительность фильма в минутах:")
        val durationInMinutes = inputReader.readIntUntilNotNull()

        filmsRepository.add(FilmAddEntity(title, actors, durationInMinutes))

        println("Фильм: $title успешно добавлен")
    }

    private fun deleteFilm() {
        println("Введите название фильма для удаления:")
        val title = inputReader.readStringUntilNotNull()

        filmsController.deleteFilm(title)
        //filmsRepository.delete(filmIdService.getIdFromTitle(title))

        println("Фильм: $title успешно удален")
    }

    private fun processEditFilmMenu() {
        showAllFilms()
        filmEditMenu.handle()
    }
}