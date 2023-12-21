package dal

import dal.entities.FilmEntity

class FilmsJsonRepository(path: String) : JsonRepository<FilmEntity>(path) {
    override fun serialize(data: Storage<FilmEntity>): String {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: String): Storage<FilmEntity> {
        TODO("Not yet implemented")
    }

}