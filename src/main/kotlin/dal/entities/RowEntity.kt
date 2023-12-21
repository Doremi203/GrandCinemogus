package dal.entities

import kotlinx.serialization.Serializable

@Serializable
data class RowEntity(
    val number: Int,
    val seats: Int,
)