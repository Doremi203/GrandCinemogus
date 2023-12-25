package dal.repositories

import dal.entities.UserAddEntity
import dal.entities.UserEntity
import dal.repositories.interfaces.UsersRepository
import kotlinx.serialization.encodeToString
import java.util.*

class UsersJsonRepository(
    path: String
) : JsonRepository<UserEntity>(path), UsersRepository {
    override fun add(item: UserAddEntity) {
        loadDataDoActionAndSave { data ->
            data.add(
                UserEntity(
                    UUID.randomUUID(),
                    item.login,
                    item.password
                )
            )
        }
    }

    override fun serialize(data: List<UserEntity>): String {
        return json.encodeToString(data)
    }

    override fun deserialize(data: String): List<UserEntity> {
        return json.decodeFromString(data)
    }
}