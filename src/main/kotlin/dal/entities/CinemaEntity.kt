package dal.entities

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CinemaEntity(
    @Serializable(with = UUIDSerializer::class)
    override val id: UUID,
    val name: String,
    val rowEntities: List<RowEntity>,
) : EntityWithId()

