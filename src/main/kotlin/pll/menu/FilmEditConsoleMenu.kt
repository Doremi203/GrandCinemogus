package pll.menu

import dal.entities.FilmUpdateEntity
import di.Di
import pll.exceptions.NoFilmsException
import java.util.*

class FilmEditConsoleMenu(

) : ConsoleMenu("Меню редактирования фильма") {

    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Редактировать название фильма", ::editFilmTitle),
        MenuItem("Редактировать актеров фильма", ::editFilmActors)
    )

    private fun editFilmTitle() {
        processEdit { filmId ->
            println("Введите новое название фильма:")
            val newTitle = Di.inputReader.readStringUntilNotNull()
            Di.filmValidator.validateTitle(newTitle)

            Di.filmsRepository.update(
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
            Di.filmValidator.validateActors(newActors)

            Di.filmsRepository.update(
                filmId,
                FilmUpdateEntity(actors = newActors)
            )

            println("Список актеров изменен на: $newActors")
        }
    }

    private fun processEdit(editActions: (UUID) -> Unit) {
        handleExceptions {
            println("Введите название фильма для редактирования:")
            val title = Di.inputReader.readStringUntilNotNull()
            Di.filmValidator.validateTitle(title)

            editActions(Di.filmIdService.getIdFromTitle(title))
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