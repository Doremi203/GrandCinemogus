package dal

import dal.entities.FilmEntity
import dal.storages.Storage
import kotlinx.serialization.encodeToString

class FilmsJsonRepository(path: String) : BaseJsonRepository<FilmEntity>(path) {
    override fun getAll(): List<FilmEntity> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): FilmEntity? {
        TODO("Not yet implemented")
    }

    override fun delete(item: FilmEntity) {
        TODO("Not yet implemented")
    }

    override fun update(item: FilmEntity) {
        TODO("Not yet implemented")
    }

    override fun add(item: FilmEntity) {
        TODO("Not yet implemented")
    }

    override fun loadFromFile(): Storage<FilmEntity> {
        val data = json.decodeFromString<Storage<FilmEntity>>(file.readText())
        return data
    }

    override fun saveToFile(data: Storage<FilmEntity>) {
        val string = json.encodeToString<Storage<FilmEntity>>(data);
        file.writeText(string)
    }
}