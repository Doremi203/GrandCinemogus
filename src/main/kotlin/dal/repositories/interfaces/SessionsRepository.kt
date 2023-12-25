package dal.repositories.interfaces

import dal.entities.SessionAddEntity
import dal.entities.SessionEntity
import dal.entities.SessionUpdateEntity
import java.util.*

interface SessionsRepository {
    fun getAll(): List<SessionEntity>
    fun getAllByFilmId(filmId: UUID): List<SessionEntity>
    fun getById(id: UUID): SessionEntity
    fun add(item: SessionAddEntity)
    fun update(id: UUID, item: SessionUpdateEntity)
    fun delete(id: UUID)
}