package models

import kotlinx.serialization.Serializable

@Serializable
data class Cinema(
    val name: String,
    val rows: MutableList<Row>,
)

