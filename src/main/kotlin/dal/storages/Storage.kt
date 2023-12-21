package dal.storages

import kotlinx.serialization.Serializable

@Serializable
abstract class Storage<T> {
    abstract val data: List<T>
}