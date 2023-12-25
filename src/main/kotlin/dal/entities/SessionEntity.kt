package dal.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class SessionEntity(
    @Serializable(with = UUIDSerializer::class)
    override val id: UUID,
    @Serializable(with = UUIDSerializer::class)
    val filmId: UUID,
    val dateTime: LocalDateTime,
    val ticketPrice: Double,
    val seats: List<List<SeatStateEntity>>,
) : EntityWithId()