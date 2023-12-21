package bll.controllers.interfaces

interface TicketsController {
    fun sellTicket(sessionId: Int, row: Int, seat: Int)
    fun returnTicket(sessionId: Int, row: Int, seat: Int)
}