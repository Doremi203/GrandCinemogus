package dal.entities

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserEntity(
    @Serializable(with = UUIDSerializer::class)
    override val id: UUID,
    val login: String,
    val password: String,
) : EntityWithId()
