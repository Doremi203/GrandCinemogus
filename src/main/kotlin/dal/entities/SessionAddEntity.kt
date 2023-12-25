package dal.entities

import kotlinx.datetime.LocalDateTime
import java.util.*

data class SessionAddEntity(
    val filmId: UUID,
    val dateTime: LocalDateTime,
    val ticketPrice: Double,
    val seats: List<List<SeatStateEntity>>,
)
