package bll.services.interfaces

import dal.entities.SeatStateEntity

interface TicketService {
    fun sellTicket(seats: MutableList<MutableList<SeatStateEntity>>, row: Int, seat: Int)
    fun returnTicket(seats: MutableList<MutableList<SeatStateEntity>>, row: Int, seat: Int)
    fun tagTicketAsVisited(seats: MutableList<MutableList<SeatStateEntity>>, row: Int, seat: Int)
}