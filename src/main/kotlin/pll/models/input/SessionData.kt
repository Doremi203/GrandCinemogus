package pll.models.input

import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class SessionData (
    val filmId: UUID,
    val dateTime: LocalDateTime,
    val ticketPrice: Double,
)
