package dal.repositories

import dal.Storage
import dal.entities.EntityWithId
import kotlinx.serialization.json.Json
import java.io.File

abstract class JsonRepository<T : EntityWithId>(path: String) {
    protected val json = Json { prettyPrint = true }
    private val file = File(path)

    fun getAllEntities(): List<T> = loadStorageFromFile().data

    fun getEntityById(id: Int): T? =
        loadStorageFromFile().data.find { it.id == id }

    fun add(item: T) =
        loadDataDoActionAndSave { data -> data.add(item) }

    fun updateIf(item: T): Boolean {
        var isUpdated = false
        loadDataDoActionAndSave { data ->
            isUpdated = data.removeIf { it.id == item.id }
            if (isUpdated)
                data.add(item)
        }
        return isUpdated
    }

    fun deleteIf(id: Int): Boolean {
        var isDeleted = false
        loadDataDoActionAndSave { data -> isDeleted = data.removeIf { it.id == id } }
        return isDeleted
    }

    protected abstract fun serialize(data: Storage<T>): String
    protected abstract fun deserialize(data: String): Storage<T>

    private fun loadDataDoActionAndSave(action: (MutableList<T>) -> Unit) {
        val storage = loadStorageFromFile()
        val dataToModify = storage.data.toMutableList()
        action(dataToModify)
        saveToFile(Storage(dataToModify))
    }

    private fun saveToFile(data: Storage<T>) {
        val jsonData = serialize(data)
        file.writeText(jsonData)
    }

    private fun loadStorageFromFile(): Storage<T> {
        val data = file.readText()
        return deserialize(data)
    }

}