package dal.entities

import kotlinx.serialization.Serializable

@Serializable
data class FilmEntity(
    val id: Int,
    val title: String,
    val actors: MutableList<String>,
)