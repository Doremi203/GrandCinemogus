package models

import kotlinx.serialization.Serializable

@Serializable
data class Row(
    val number: Int,
    val seats: Int,
)