package dal

import dal.storages.Storage
import java.io.File

abstract class BaseFileHandler<T : Storage<R>, R>(
    path: String
) {
    protected val file = File(path)
    abstract fun save(data: T)
    abstract fun load(): T
}