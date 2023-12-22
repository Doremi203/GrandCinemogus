package dal.repositories

import dal.entities.SessionEntity
import kotlinx.serialization.encodeToString

class SessionsJsonRepository(path: String) : JsonRepository<SessionEntity>(path) {
    override fun serialize(data: List<SessionEntity>): String {
        return json.encodeToString(data)
    }

    override fun deserialize(data: String): List<SessionEntity> {
        return json.decodeFromString(data)
    }

}