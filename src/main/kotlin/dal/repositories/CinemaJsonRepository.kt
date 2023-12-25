package dal.repositories

import dal.entities.CinemaAddEntity
import dal.entities.CinemaEntity
import dal.repositories.interfaces.CinemaRepository
import kotlinx.serialization.encodeToString
import java.util.*

class CinemaJsonRepository(
    path: String
) : JsonRepository<CinemaEntity>(path), CinemaRepository {
    override fun add(item: CinemaAddEntity) {
        loadDataDoActionAndSave { data ->
            data.add(
                CinemaEntity(
                    UUID.randomUUID(),
                    item.name,
                    item.rowEntities
                )
            )
        }
    }

    override fun serialize(data: List<CinemaEntity>): String {
        return json.encodeToString(data)
    }

    override fun deserialize(data: String): List<CinemaEntity> {
        return json.decodeFromString(data)
    }
}