package dal.repositories

import dal.entities.EntityWithId
import dal.exceptions.ElementAlreadyPresentException
import kotlinx.serialization.json.Json
import java.io.File

abstract class JsonRepository<T : EntityWithId>(path: String) {
    protected val json = Json { prettyPrint = true }
    private val file = File(path)

    fun getAllEntities(): List<T> = loadStorageFromFile()

    fun getEntityById(id: Int): T =
        loadStorageFromFile().find { it.id == id }
            ?: throw NoSuchElementException("No element with id $id")

    fun add(item: T) {
        loadDataDoActionAndSave { data ->
            if (data.any { it.id == item.id })
                throw ElementAlreadyPresentException("Element with id ${item.id} already exists")
            data.add(item) }
    }

    fun update(item: T) {
        loadDataDoActionAndSave { data ->
            val isUpdated = data.removeIf { it.id == item.id }
            if (!isUpdated)
                throw NoSuchElementException("No element with id ${item.id}")
            data.add(item)
        }
    }

    fun updateIf(item: T): Boolean {
        var isUpdated = false
        loadDataDoActionAndSave { data ->
            isUpdated = data.removeIf { it.id == item.id }
            if (isUpdated)
                data.add(item)
        }
        return isUpdated
    }

    fun delete(id: Int) {
        loadDataDoActionAndSave { data ->
            val isDeleted = data.removeIf { it.id == id }
            if (!isDeleted)
                throw NoSuchElementException("No element with id $id")
        }
    }

    fun deleteIf(id: Int): Boolean {
        var isDeleted = false
        loadDataDoActionAndSave { data -> isDeleted = data.removeIf { it.id == id } }
        return isDeleted
    }

    protected abstract fun serialize(data: List<T>): String
    protected abstract fun deserialize(data: String): List<T>

    private fun loadDataDoActionAndSave(action: (MutableList<T>) -> Unit) {
        val data = loadStorageFromFile()
        val dataToModify = data.toMutableList()
        action(dataToModify)
        saveToFile(dataToModify)
    }

    private fun saveToFile(data: List<T>) {
        val jsonData = serialize(data)
        file.writeText(jsonData)
    }

    private fun loadStorageFromFile(): List<T> {
        val data = file.readText()
        return deserialize(data)
    }

}