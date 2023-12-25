package dal.entities

import kotlinx.datetime.LocalDateTime

data class SessionUpdateEntity(
    val dateTime: LocalDateTime?,
    val ticketPrice: Double?,
)
