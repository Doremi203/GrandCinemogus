package dal.entities

import kotlinx.datetime.LocalDateTime

data class SessionUpdateEntity(
    val dateTime: LocalDateTime? = null,
    val ticketPrice: Double? = null,
    val seats: List<List<SeatStateEntity>>? = null
)
