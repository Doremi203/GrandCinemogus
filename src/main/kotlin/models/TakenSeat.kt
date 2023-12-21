package models

import kotlinx.serialization.Serializable

@Serializable
data class TakenSeat(
    val row: Int,
    val seat: Int,
)