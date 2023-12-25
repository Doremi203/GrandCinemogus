package bll.controllers

import bll.controllers.interfaces.FilmsController
import bll.services.interfaces.FilmIdService
import dal.repositories.interfaces.FilmsRepository
import dal.repositories.interfaces.SessionsRepository

class DefaultFilmsController(
    private val sessionsRepository: SessionsRepository,
    private val filmsRepository: FilmsRepository,
    private val filmIdService: FilmIdService
) : FilmsController {
    override fun deleteFilm(title: String) {
        val id = filmIdService.getIdFromTitle(title)

        val sessionsForFilm = sessionsRepository.getAllByFilmId(id)
        sessionsForFilm.forEach { sessionsRepository.delete(it.id) }

        filmsRepository.delete(id)
    }
}