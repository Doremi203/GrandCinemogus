package bll.services

import dal.repositories.interfaces.FilmsRepository
import java.util.*

class DefaultFilmIdService(
    private val filmsRepository: FilmsRepository
) : FilmIdService {
    override fun getIdFromTitle(title: String): UUID {
        val films = filmsRepository.getAll()
        val film = films.find { it.title == title }
            ?: throw NoSuchElementException("Нет фильма с названием: $title")
        return film.id
    }
}