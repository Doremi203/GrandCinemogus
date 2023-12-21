package models

import kotlinx.serialization.Serializable

@Serializable
data class SoldSeat(
    val row: Int,
    val seat: Int,
)