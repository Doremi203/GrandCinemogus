package models

import kotlinx.serialization.Serializable

@Serializable
data class Storage(
    val cinema: Cinema,
    val films: List<Film>,
    val sessions: List<Session>,
)