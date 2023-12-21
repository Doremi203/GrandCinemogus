package dal.storages

import dal.entities.SessionEntity
import kotlinx.serialization.Serializable

@Serializable
data class SessionsStorage(
    override val data: List<SessionEntity>
) : Storage<SessionEntity>()
