package bll.controllers

import bll.controllers.interfaces.FilmValidator
import dal.entities.FilmEntity
import dal.repositories.JsonRepository

class DefaultFilmValidator(
    private val filmsRepository: JsonRepository<FilmEntity>
) : FilmValidator {
    override fun validateTitle(title: String): Boolean {
        if (title.isEmpty()) {
            return false
        }

        val films = filmsRepository.getAllEntities()
        return films.none { it.title == title }
    }

     override fun validateActors(actors: List<String>): Boolean {
        return actors.isNotEmpty()
    }
}