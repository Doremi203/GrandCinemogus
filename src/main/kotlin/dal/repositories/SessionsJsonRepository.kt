package dal.repositories

import dal.entities.SessionAddEntity
import dal.entities.SessionEntity
import dal.entities.SessionUpdateEntity
import dal.repositories.interfaces.SessionsRepository
import kotlinx.serialization.encodeToString
import java.util.*

class SessionsJsonRepository(
    path: String
) : JsonRepository<SessionEntity>(path), SessionsRepository {
    override fun getAllByFilmId(filmId: UUID): List<SessionEntity> {
        return getAll().filter { it.filmId == filmId }
    }

    override fun add(item: SessionAddEntity) {
        loadDataDoActionAndSave { data ->
            data.add(
                SessionEntity(
                    UUID.randomUUID(),
                    item.filmId,
                    item.dateTime,
                    item.ticketPrice,
                    item.seats
                )
            )
        }
    }

    override fun update(id: UUID, item: SessionUpdateEntity) {
        loadDataDoActionAndSave { data ->
            val oldSession = data.find { it.id == id }
                ?: throw NoSuchElementException("Нет сеанса с таким $id")
            data.remove(oldSession)

            data.add(
                oldSession.copy(
                    dateTime = item.dateTime ?: oldSession.dateTime,
                    ticketPrice = item.ticketPrice ?: oldSession.ticketPrice,
                )
            )
        }
    }

    override fun serialize(data: List<SessionEntity>): String = json.encodeToString(data)

    override fun deserialize(data: String): List<SessionEntity> = json.decodeFromString(data)
}