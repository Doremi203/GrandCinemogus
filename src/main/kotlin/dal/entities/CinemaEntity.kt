package dal.entities

import kotlinx.serialization.Serializable

@Serializable
data class CinemaEntity(
    val id: Int,
    val name: String,
    val rowEntities: MutableList<RowEntity>,
)

