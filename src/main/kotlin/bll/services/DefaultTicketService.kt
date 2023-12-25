package bll.services

import bll.exceptions.TicketAlreadySoldException
import bll.exceptions.TicketAlreadyUsedException
import bll.exceptions.TicketNotSoldException
import dal.entities.SeatStateEntity
import dal.repositories.interfaces.SessionsRepository

class DefaultTicketService(
    private val sessionsRepository: SessionsRepository,
) : TicketService {
    override fun sellTicket(
        seats: MutableList<MutableList<SeatStateEntity>>,
        row: Int,
        seat: Int
    ) {
        if (seats[row - 1][seat - 1] == SeatStateEntity.SOLD)
            throw TicketAlreadySoldException("Место ($row, $seat) уже куплено")

        seats[row - 1][seat - 1] = SeatStateEntity.SOLD
    }

    override fun returnTicket(
        seats: MutableList<MutableList<SeatStateEntity>>,
        row: Int,
        seat: Int
    ) {
        if (seats[row - 1][seat - 1] == SeatStateEntity.FREE)
            throw TicketNotSoldException("Место ($row, $seat) не было куплено")

        seats[row - 1][seat - 1] = SeatStateEntity.FREE
    }

    override fun tagTicketAsVisited(
        seats: MutableList<MutableList<SeatStateEntity>>,
        row: Int,
        seat: Int
    ) {
        if (seats[row - 1][seat - 1] == SeatStateEntity.FREE)
            throw TicketNotSoldException("Место ($row, $seat) не было куплено")
        if (seats[row - 1][seat - 1] == SeatStateEntity.TAKEN)
            throw TicketAlreadyUsedException("Билет ($row, $seat) уже был использован")

        seats[row - 1][seat - 1] = SeatStateEntity.TAKEN
    }
}