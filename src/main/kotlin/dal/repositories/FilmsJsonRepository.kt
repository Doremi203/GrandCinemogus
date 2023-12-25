package dal.repositories

import dal.entities.FilmAddEntity
import dal.entities.FilmEntity
import dal.entities.FilmUpdateEntity
import dal.exceptions.ElementAlreadyPresentException
import dal.exceptions.IdNotPresentException
import dal.repositories.interfaces.FilmsRepository
import kotlinx.serialization.encodeToString
import java.util.*

class FilmsJsonRepository(
    path: String
) : JsonRepository<FilmEntity>(path), FilmsRepository {
    override fun add(item: FilmAddEntity) {
        loadDataDoActionAndSave { data ->
            val isPresent = data.any { it.title == item.title }
            if (isPresent)
                throw ElementAlreadyPresentException(
                    "Фильм с названием ${item.title} уже содержится в базе"
                )

            data.add(FilmEntity(UUID.randomUUID(), item.title, item.actors, item.durationInMinutes))
        }
    }

    override fun update(id: UUID, item: FilmUpdateEntity) {
        loadDataDoActionAndSave { data ->
            val oldFilm = data.find { it.id == id }
                ?: throw IdNotPresentException("Нет фильма с таким $id")
            data.remove(oldFilm)

            data.add(
                oldFilm.copy(
                    title = item.title ?: oldFilm.title,
                    actors = item.actors ?: oldFilm.actors
                )
            )
        }
    }

    override fun serialize(data: List<FilmEntity>): String {
        return json.encodeToString(data)
    }

    override fun deserialize(data: String): List<FilmEntity> {
        return json.decodeFromString(data)
    }
}