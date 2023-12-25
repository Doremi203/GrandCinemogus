package dal.entities

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class FilmEntity(
    @Serializable(with = UUIDSerializer::class)
    override val id: UUID,
    val title: String,
    val actors: List<String>,
    val durationInMinutes: Int
) : EntityWithId()