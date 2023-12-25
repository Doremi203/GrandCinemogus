package bll.controllers.interfaces

import java.util.*

interface TicketsController {
    fun sellTicket(sessionId: UUID, rowNumber: Int, seatNumber: Int)
    fun returnTicket(sessionId: UUID, rowNumber: Int, seatNumber: Int)
    fun tagTicketAsVisited(sessionId: UUID, rowNumber: Int, seatNumber: Int)
}