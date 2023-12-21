package dal

import dal.entities.SessionEntity
import kotlinx.serialization.encodeToString

class SessionsJsonRepository(path: String) : JsonRepository<SessionEntity>(path) {
    override fun serialize(data: Storage<SessionEntity>): String {
        return json.encodeToString(data)
    }

    override fun deserialize(data: String): Storage<SessionEntity> {
        return json.decodeFromString(data)
    }

}