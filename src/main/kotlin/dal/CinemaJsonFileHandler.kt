package dal

import dal.entities.CinemaEntity
import dal.storages.CinemaStorage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CinemaJsonFileHandler(path: String) : BaseFileHandler<CinemaStorage, CinemaEntity>(path) {
    private val json = Json { prettyPrint = true }
    override fun save(data: CinemaStorage) {
        val jsonData = json.encodeToString(data)
        file.writeText(jsonData)
    }

    override fun load(): CinemaStorage {
        val data = file.readText()
        return json.decodeFromString(data)
    }
}