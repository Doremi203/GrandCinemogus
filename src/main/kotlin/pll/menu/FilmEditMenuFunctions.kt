package pll.menu

import dal.entities.FilmUpdateEntity
import di.Di
import pll.exceptions.NoFilmsException
import java.util.*

fun editFilmTitle() {
    processEdit { filmId ->
        println("Введите новое название фильма:")
        val newTitle = Di.inputReader.readStringUntilNotNull()
        Di.filmValidator.validateTitle(newTitle)

        Di.filmsRepository.update(
            filmId,
            FilmUpdateEntity(title = newTitle)
        )
    }
}

fun editFilmActors() {
    processEdit { filmId ->
        println("Введите новых актеров фильма через запятую:")
        val newActors = Di.inputReader.readStringUntilNotNull()
            .split(",")
        Di.filmValidator.validateActors(newActors)

        Di.filmsRepository.update(
            filmId,
            FilmUpdateEntity(actors = newActors)
        )
    }
}

private fun processEdit(editActions: (UUID) -> Unit) {
    handleExceptions {
        showAllFilms()

        println("Введите название фильма для редактирования:")
        val title = Di.inputReader.readStringUntilNotNull()
        Di.filmValidator.validateTitle(title)

        val res = editActions(Di.filmIdService.getFilmIdFromTitle(title))
        println(res)
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