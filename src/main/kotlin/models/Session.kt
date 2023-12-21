package models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val id: Int,
    val filmId: Int,
    val dateTime: LocalDateTime,
    val ticketPrice: Double,
    val seats: MutableList<MutableList<SeatState>>,
)