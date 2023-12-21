package dal.entities

sealed class EntityWithId {
    abstract val id: Int
}