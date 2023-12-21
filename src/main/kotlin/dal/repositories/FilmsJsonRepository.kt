package dal.repositories

import dal.Storage
import dal.entities.FilmEntity
import kotlinx.serialization.encodeToString

class FilmsJsonRepository(path: String) : JsonRepository<FilmEntity>(path) {
    override fun serialize(data: Storage<FilmEntity>): String {
        return json.encodeToString(data)
    }

    override fun deserialize(data: String): Storage<FilmEntity> {
        return json.decodeFromString(data)
    }

}