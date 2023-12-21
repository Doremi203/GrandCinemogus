package dal.storages

import kotlinx.serialization.Serializable

@Serializable
sealed class Storage<T> {
    abstract val data: List<T>
}