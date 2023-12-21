package dal

import dal.entities.SessionEntity
import dal.storages.SessionsStorage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SessionsJsonFileHandler(path: String) : BaseFileHandler<SessionsStorage, SessionEntity>(path) {
    private val json = Json { prettyPrint = true }
    override fun save(data: SessionsStorage) {
        val jsonData = json.encodeToString(data)
        file.writeText(jsonData)
    }

    override fun load(): SessionsStorage {
        val data = file.readText()
        return json.decodeFromString(data)
    }
}