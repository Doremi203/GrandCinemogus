package bll.controllers

import bll.controllers.interfaces.FilmValidator
import bll.controllers.interfaces.FilmsController
import bll.exceptions.InvalidIdException
import dal.entities.FilmEntity
import dal.exceptions.ElementAlreadyPresentException
import dal.repositories.JsonRepository
import pll.models.input.FilmData
import pll.models.output.FilmOutput

class DefaultFilmsController(
    private val filmRepository: JsonRepository<FilmEntity>,
    private val filmValidator: FilmValidator
) : FilmsController {
    override fun getAllFilms(): List<FilmOutput> {
        val films = filmRepository.getAllEntities()
        return films.map { FilmOutput(it.id, it.title, it.actors) }
    }

    override fun getFilmById(filmId: Int): FilmOutput {
        val film = try {
            filmRepository.getEntityById(filmId)
        } catch (e: NoSuchElementException) {
            throw InvalidIdException("Нету фильма с id $filmId", filmId)
        }
        return FilmOutput(film.id, film.title, film.actors)
    }

    override fun addFilm(newFilmData: FilmData): Result<String> {
        val films = filmRepository.getAllEntities().toMutableList()
        if (!filmValidator.validateTitle(newFilmData.title))
            throw IllegalArgumentException("Название фильма не валидно")
        if (!filmValidator.validateActors(newFilmData.actors))
            throw IllegalArgumentException("Список актеров не валиден")

        val maxId = films.maxOfOrNull { it.id } ?: 0
        val newId = maxId + 1

        val newFilm = FilmEntity(
            id = newId,
            title = newFilmData.title,
            actors = newFilmData.actors,
        )

        films.add(newFilm)

        filmRepository.add(newFilm)

        return Result.success("Фильм с id $newId был добавлен")
    }

    override fun deleteFilm(filmId: Int): Result<String> {
        try {
            filmRepository.delete(filmId)
        } catch (e: NoSuchElementException) {
            throw IllegalArgumentException("Фильм с таким id $filmId не существует")
        }
        return Result.success("Фильм с id $filmId был удален")
    }

    /*override fun editFilmTitle(filmId: Int, newTitle: String): Result<String> {
        handleExceptions {
            val film = filmRepository.getEntityById(filmId)
            val newFilm = film.copy(title = newTitle)
            filmRepository.update(newFilm)
        }
        return Result.success("Название фильма с id $filmId было изменено")
    }*/

    override fun editFilmTitle(filmId: Int, newTitle: String): Result<String> {
        try {
            val film = filmRepository.getEntityById(filmId)
            val newFilm = film.copy(title = newTitle)
            filmRepository.update(newFilm)
        } catch (e: NoSuchElementException) {
            throw IllegalArgumentException("Фильм с таким id $filmId не существует")
        }

        return Result.success("Название фильма с id $filmId было изменено")
    }

    override fun editFilmActors(filmId: Int, newActors: List<String>): Result<String> {
        TODO("Not yet implemented")
    }


    private fun handleExceptions(action: () -> Unit) {
        try {
            action()
        } catch (e: NoSuchElementException) {
            throw IllegalArgumentException("No film with this id")
        } catch (e: ElementAlreadyPresentException) {
            throw IllegalStateException("Film with this id already exists")
        }
    }
}