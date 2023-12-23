package bll.services

import dal.repositories.FilmsRepository
import java.util.*

class DefaultFilmIdService(
    private val filmsRepository: FilmsRepository
) : FilmIdService {
    override fun getFilmIdFromTitle(title: String): UUID {
        val films = filmsRepository.getAll()
        val film = films.find { it.title == title }
            ?: throw NoSuchElementException("Нет фильма с названием: $title")
        return film.id
    }
}