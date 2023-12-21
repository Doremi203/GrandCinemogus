package dal

import dal.entities.CinemaEntity
import kotlinx.serialization.encodeToString

class CinemaJsonRepository(path: String) : JsonRepository<CinemaEntity>(path) {
    override fun serialize(data: Storage<CinemaEntity>): String {
        return json.encodeToString(data)
    }

    override fun deserialize(data: String): Storage<CinemaEntity> {
        return json.decodeFromString(data)
    }

}