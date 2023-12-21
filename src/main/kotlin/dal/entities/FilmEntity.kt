package dal.entities

import kotlinx.serialization.Serializable

@Serializable
data class FilmEntity(
    override val id: Int,
    val title: String,
    val actors: MutableList<String>,
) : EntityWithId()