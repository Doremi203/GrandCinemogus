package pll.menu

import bll.exceptions.ControllerException
import di.Di

fun editFilmTitle() {
    processEdit { filmId ->
        println("Введите новое название фильма:")
        val newTitle = Di.inputReader.readStringUntilNotNull()
        Di.filmsController.editFilmTitle(filmId, newTitle)
    }
}

fun editFilmActors() {
    processEdit { filmId ->
        println("Введите новых актеров фильма через запятую:")
        val newActors = Di.inputReader.readStringUntilNotNull()
        Di.filmsController.editFilmActors(filmId, newActors.split(","))
    }
}

private fun processEdit(editActions: (Int) -> Result<String>) {
    handleExceptions {
        println("Введите id фильма:")
        val filmId = Di.inputReader.readIntUntilNotNull()
        val res = editActions(filmId)
        println(res)
    }
}

private fun handleExceptions(block: () -> Unit) {
    try {
        block()
    } catch (e: IllegalStateException) {
        println(e.message)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    } catch (e: ControllerException) {
        println(e.message)
    }
}