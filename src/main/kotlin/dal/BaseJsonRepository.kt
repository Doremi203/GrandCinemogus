package dal

import dal.storages.Storage
import kotlinx.serialization.json.Json
import java.io.File

abstract class BaseJsonRepository<T>(
    path: String,
) {
    protected val file = File(path)
    protected val json = Json { prettyPrint = true }
    protected val storage: Storage<T>
            get() = loadFromFile()
    abstract fun getAll(): List<T>
    abstract fun getById(id: Int): T?
    abstract fun add(item: T)
    abstract fun update(item: T)
    abstract fun delete(item: T)
    protected abstract fun saveToFile(data: Storage<T>)
    protected abstract fun loadFromFile(): Storage<T>
}