package dal

import kotlinx.serialization.Serializable

@Serializable
data class Storage<T> (
    val data: List<T>
)