package dal.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class SessionEntity(
    override val id: Int,
    val filmId: Int,
    val dateTime: LocalDateTime,
    val ticketPrice: Double,
    val seats: List<List<SeatStateEntity>>,
) : EntityWithId()