package dal

import dal.entities.FilmEntity
import dal.storages.FilmsStorage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FilmsJsonFileHandler(path: String) : BaseFileHandler<FilmsStorage, FilmEntity>(path) {
    private val json = Json { prettyPrint = true }
    override fun save(data: FilmsStorage) {
        val jsonData = json.encodeToString(data)
        file.writeText(jsonData)
    }

    override fun load(): FilmsStorage {
        val data = file.readText()
        return json.decodeFromString(data)
    }
}