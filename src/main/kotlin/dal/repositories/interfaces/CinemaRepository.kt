package dal.repositories.interfaces

import dal.entities.*
import java.util.*

interface CinemaRepository {
    fun getAll(): List<CinemaEntity>
    fun getById(id: UUID): CinemaEntity
    fun add(item: CinemaAddEntity)
    fun delete(id: UUID)
}