package dal.entities

import kotlinx.serialization.Serializable

@Serializable
data class CinemaEntity(
    override val id: Int,
    val name: String,
    val rowEntities: List<RowEntity>,
) : EntityWithId()

