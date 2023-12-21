package dal.entities

import kotlinx.serialization.Serializable

@Serializable
data class CinemaEntity(
    val name: String,
    val rowEntities: MutableList<RowEntity>,
)

