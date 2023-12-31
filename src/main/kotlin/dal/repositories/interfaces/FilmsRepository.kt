package dal.repositories.interfaces

import dal.entities.FilmAddEntity
import dal.entities.FilmEntity
import dal.entities.FilmUpdateEntity
import java.util.*

interface FilmsRepository {
    fun getAll(): List<FilmEntity>
    fun getById(id: UUID): FilmEntity
    fun add(item: FilmAddEntity)
    fun update(id: UUID, item: FilmUpdateEntity)
    fun delete(id: UUID)
}