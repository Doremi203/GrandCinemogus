package pll.menu

import bll.services.interfaces.FilmIdService
import bll.validators.interfaces.FilmValidator
import dal.entities.FilmUpdateEntity
import dal.repositories.interfaces.FilmsRepository
import di.Di
import pll.InputReader
import pll.exceptions.NoFilmsException
import java.util.*

class FilmEditConsoleMenu(
    private val filmsRepository: FilmsRepository,
    private val filmIdService: FilmIdService,
    private val filmValidator: FilmValidator,
    private val inputReader: InputReader
) : ConsoleMenu("Меню редактирования фильма") {

    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Редактировать название фильма", ::editFilmTitle),
        MenuItem("Редактировать актеров фильма", ::editFilmActors),
        MenuItem("Редактировать длительность фильма", ::editFilmDuration)
    )

    private fun editFilmTitle() {
        processEdit { filmId ->
            println("Введите новое название фильма:")
            val newTitle = inputReader.readStringUntilNotNull()
            filmValidator.validateTitle(newTitle)

            filmsRepository.update(
                filmId,
                FilmUpdateEntity(title = newTitle)
            )

            println("Название фильма успешно изменено на: $newTitle")
        }
    }

    private fun editFilmActors() {
        processEdit { filmId ->
            println("Введите новых актеров фильма через запятую:")
            val newActors = Di.inputReader.readStringUntilNotNull()
                .split(",")
            filmValidator.validateActors(newActors)

            filmsRepository.update(
                filmId,
                FilmUpdateEntity(actors = newActors)
            )

            println("Список актеров изменен на: $newActors")
        }
    }

    private fun editFilmDuration() {
        processEdit { filmId ->
            println("Введите новую длительность фильма:")
            val newDuration = inputReader.readIntUntilNotNull()
            filmValidator.validateDuration(newDuration)

            filmsRepository.update(
                filmId,
                FilmUpdateEntity(durationInMinutes = newDuration)
            )

            println("Длительность фильма изменена на: $newDuration")
        }
    }

    private fun processEdit(editActions: (UUID) -> Unit) {
        handleExceptions {
            println("Введите название фильма для редактирования:")
            val title = inputReader.readStringUntilNotNull()
            filmValidator.validateTitle(title)

            editActions(filmIdService.getIdFromTitle(title))
        }
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
            }
        }
    }
}