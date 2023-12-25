package bll.controllers

import bll.controllers.interfaces.TicketsController
import bll.services.interfaces.TicketService
import dal.entities.SeatStateEntity
import dal.entities.SessionUpdateEntity
import dal.repositories.interfaces.SessionsRepository
import java.util.*

class DefaultTicketsController(
    private val sessionsRepository: SessionsRepository,
    private val ticketsService: TicketService
) : TicketsController {
    override fun sellTicket(sessionId: UUID, rowNumber: Int, seatNumber: Int) {
        performTicketOperation(sessionId, rowNumber, seatNumber) { seats, row, seat ->
            ticketsService.sellTicket(seats, row, seat)
        }
    }

    override fun returnTicket(sessionId: UUID, rowNumber: Int, seatNumber: Int) {
        performTicketOperation(sessionId, rowNumber, seatNumber) { seats, row, seat ->
            ticketsService.returnTicket(seats, row, seat)
        }
    }

    override fun tagTicketAsVisited(sessionId: UUID, rowNumber: Int, seatNumber: Int) {
        performTicketOperation(sessionId, rowNumber, seatNumber) { seats, row, seat ->
            ticketsService.tagTicketAsVisited(seats, row, seat)
        }
    }

    private fun performTicketOperation(
        sessionId: UUID,
        row: Int,
        seat: Int,
        action: (MutableList<MutableList<SeatStateEntity>>, Int, Int) -> Unit
    ) {
        val session = sessionsRepository.getById(sessionId)
        val seats = session.seats.map { it.toMutableList() }.toMutableList()

        action(seats, row, seat)

        sessionsRepository.update(
            sessionId, SessionUpdateEntity(
                seats = seats
            )
        )
    }

}