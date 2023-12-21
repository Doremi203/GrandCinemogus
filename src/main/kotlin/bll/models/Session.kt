package bll.models

import kotlinx.datetime.LocalDateTime

data class Session(
    val id: Int,
    val filmId: Int,
    val dateTime: LocalDateTime,
    val ticketPrice: Double,
    val seats: List<List<SeatState>>,
)