package bll.controllers

import bll.controllers.interfaces.SessionsController
import dal.entities.RowEntity
import dal.entities.SeatStateEntity
import dal.entities.SessionAddEntity
import dal.repositories.interfaces.CinemaRepository
import dal.repositories.interfaces.SessionsRepository
import pll.models.input.SessionData
import java.util.*

class DefaultSessionsController(
    private val sessionsRepository: SessionsRepository,
    private val cinemaRepository: CinemaRepository,
    private val cinemaId: UUID
) : SessionsController {
    override fun addSession(newSessionData: SessionData) {
        val cinema = cinemaRepository.getById(cinemaId)
        val seats = cinema.rowEntities.map { rowEntity: RowEntity ->
            List(rowEntity.seats) { SeatStateEntity.FREE }
        }

        val session = SessionAddEntity(
            newSessionData.filmId,
            newSessionData.dateTime,
            newSessionData.ticketPrice,
            seats
        )

        sessionsRepository.add(session)
    }
}