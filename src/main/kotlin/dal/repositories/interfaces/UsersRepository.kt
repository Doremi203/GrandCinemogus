package dal.repositories.interfaces

import dal.entities.UserAddEntity
import dal.entities.UserEntity

interface UsersRepository {
    fun getAll(): List<UserEntity>
    fun add(item: UserAddEntity)
}