package pll.models.input

import kotlinx.datetime.LocalDateTime

data class SessionData (
    val filmId: Int,
    val dateTime: LocalDateTime,
    val ticketPrice: Double,
)
