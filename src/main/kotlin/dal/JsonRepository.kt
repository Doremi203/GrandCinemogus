package dal

import kotlinx.serialization.json.Json
import java.io.File

abstract class JsonRepository<T>(path: String) {
    private val file = File(path)
    protected val json = Json { prettyPrint = true }
    private val storage: Storage<T>
        get() = loadFromFile()

    fun getAll(): List<T> {
        return storage.data
    }
    fun add(item: T) {
        doActionWithData { data -> data.add(item) }
    }
    fun update(item: T) {
        doActionWithData { data ->
            val index = data.indexOfFirst { it == item }
            data[index] = item
        }
    }
    fun delete(item: T) {
        doActionWithData { data -> data.removeIf { it == item } }
    }

    private fun doActionWithData(action: (MutableList<T>) -> Unit) {
        val data = storage.data.toMutableList()
        action(data)
        saveToFile(Storage(data))
    }

    private fun saveToFile(data: Storage<T>) {
        val jsonData = serialize(data)
        file.writeText(jsonData)
    }
    private fun loadFromFile(): Storage<T> {
        val data = file.readText()
        return deserialize(data)
    }
    protected abstract fun serialize(data: Storage<T>): String
    protected abstract fun deserialize(data: String): Storage<T>

}