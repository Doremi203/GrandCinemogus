package pll.menu

import bll.controllers.interfaces.FilmValidator
import bll.controllers.interfaces.FilmsController
import dal.entities.FilmAddEntity
import dal.repositories.interfaces.FilmsRepository
import pll.InputReader
import pll.exceptions.NoFilmsException
import pll.models.output.FilmOutput

class FilmsConsoleMenu(
    private val filmsRepository: FilmsRepository,
    private val filmsController: FilmsController,
    private val filmValidator: FilmValidator,
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
        val films = filmsRepository.getAll().map {
            FilmOutput(it.title, it.actors, it.durationInMinutes)
        }

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
        handleExceptions {
            println("Введите название фильма:")
            val title = inputReader.readStringUntilNotNull()
            filmValidator.validateTitle(title)

            println("Введите актеров через запятую:")
            val actors = readlnOrNull()?.split(",")?.toMutableList() ?: mutableListOf()
            filmValidator.validateActors(actors)

            println("Введите длительность фильма в минутах:")
            val durationInMinutes = inputReader.readIntUntilNotNull()
            filmValidator.validateDuration(durationInMinutes)

            filmsRepository.add(FilmAddEntity(title, actors, durationInMinutes))

            println("Фильм: $title успешно добавлен")
        }
    }

    private fun deleteFilm() {
        handleExceptions {
            showAllFilms()
            println("Введите название фильма для удаления:")
            val title = inputReader.readStringUntilNotNull()

            filmsController.deleteFilm(title)

            println("Фильм: $title успешно удален")
        }
    }

    private fun processEditFilmMenu() {
        showAllFilms()
        do {
            var isExit = false
            try {
                filmEditMenu.show()
                isExit = !filmEditMenu.processInputIfNotExit()
            } catch (e: Exception) {
                println("Произошла ошибка: ${e.message}")
            }
        } while(!isExit)
    }

    private fun handleExceptions(block: () -> Unit) {
        while (true) {
            try {
                block()
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: NoSuchElementException) {
                println(e.message)
            } catch (e: NoFilmsException) {
                println(e.message)
                break
            } catch (e: NumberFormatException) {
                println("Некорректное значение для чисел")
            }
        }
    }
}