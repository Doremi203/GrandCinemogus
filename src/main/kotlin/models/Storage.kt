package models

import kotlinx.serialization.Serializable

@Serializable
data class Storage(
    val cinema: Cinema,
    val films: MutableList<Film>,
    val sessions: MutableList<Session>,
)