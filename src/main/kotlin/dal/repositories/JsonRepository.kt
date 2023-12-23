package dal.repositories

import dal.entities.EntityWithId
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

abstract class JsonRepository<T : EntityWithId>(path: String) {
    protected val json = Json { prettyPrint = true }
    private val file = File(path)

    fun getAll(): List<T> = loadFromFile()

    fun getById(id: UUID): T =
        loadFromFile().find { it.id == id }
            ?: throw NoSuchElementException("Нет элемента с таким id $id")

    fun delete(id: UUID) {
        loadDataDoActionAndSave { data ->
            val isDeleted = data.removeIf { it.id == id }
            if (!isDeleted)
                throw NoSuchElementException("Нет элемента с таким id $id")
        }
    }

    protected abstract fun serialize(data: List<T>): String
    protected abstract fun deserialize(data: String): List<T>

    protected fun loadDataDoActionAndSave(action: (MutableList<T>) -> Unit) {
        val data = loadFromFile()
        val dataToModify = data.toMutableList()
        action(dataToModify)
        saveToFile(dataToModify)
    }

    protected fun loadFromFile(): List<T> {
        val data = file.readText()
        return deserialize(data)
    }

    private fun saveToFile(data: List<T>) {
        val jsonData = serialize(data)
        file.writeText(jsonData)
    }

}