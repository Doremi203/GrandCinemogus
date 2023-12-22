package dal.repositories

import dal.entities.FilmEntity
import kotlinx.serialization.encodeToString

class FilmsJsonRepository(path: String) : JsonRepository<FilmEntity>(path) {
    override fun serialize(data: List<FilmEntity>): String {
        return json.encodeToString(data)
    }

    override fun deserialize(data: String): List<FilmEntity> {
        return json.decodeFromString(data)
    }

}