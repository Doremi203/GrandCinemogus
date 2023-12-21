package bll.controllers

interface TicketsController {
    fun sellTicket(sessionId: Int, row: Int, seat: Int)
    fun returnTicket(sessionId: Int, row: Int, seat: Int)
}