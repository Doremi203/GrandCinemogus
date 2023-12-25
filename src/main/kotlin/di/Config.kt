package di

import dal.entities.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Config(
    val filmsJsonPath: String,
    val sessionsJsonPath: String,
    val cinemaJsonPath: String,
    val usersJsonPath: String,
    @Serializable(with = UUIDSerializer::class)
    val cinemaId: UUID,
)
