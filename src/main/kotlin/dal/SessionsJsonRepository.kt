package dal

import dal.entities.SessionEntity
import dal.storages.SessionsStorage
import dal.storages.Storage
import kotlinx.serialization.encodeToString

class SessionsJsonRepository(path: String) : BaseJsonRepository<SessionEntity>(path) {
    override fun getAll(): List<SessionEntity> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): SessionEntity? {
        TODO("Not yet implemented")
    }

    override fun delete(item: SessionEntity) {
        TODO("Not yet implemented")
    }

    override fun update(item: SessionEntity) {
        TODO("Not yet implemented")
    }

    override fun add(item: SessionEntity) {
        val data = storage.data.toMutableList()
        data.add(item)
        saveToFile(SessionsStorage(data))
    }

    override fun loadFromFile(): Storage<SessionEntity> {
        val data = json.decodeFromString<Storage<SessionEntity>>(file.readText())
        return data
    }

    override fun saveToFile(data: Storage<SessionEntity>) {
        val string = json.encodeToString<Storage<SessionEntity>>(data);
        file.writeText(string)
    }
}