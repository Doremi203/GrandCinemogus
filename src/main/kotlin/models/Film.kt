package models

import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val id: Int,
    val title: String,
    val actors: MutableList<String>,
    val sessionsIds: MutableList<Int>,
)