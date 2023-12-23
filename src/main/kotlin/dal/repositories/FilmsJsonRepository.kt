package dal.repositories

import dal.entities.FilmAddEntity
import dal.entities.FilmEntity
import dal.entities.FilmUpdateEntity
import dal.exceptions.ElementAlreadyPresentException
import dal.exceptions.IdNotPresentException
import kotlinx.serialization.encodeToString
import java.util.*

class FilmsJsonRepository(
    path: String
) : JsonRepository<FilmEntity>(path), FilmsRepository {
    override fun getByTitle(title: String): FilmEntity {
        return loadFromFile().find { it.title == title }
            ?: throw NoSuchElementException("Нет фильма с названием: $title")
    }

    override fun add(item: FilmAddEntity) {
        loadDataDoActionAndSave { data ->
            val isPresent = data.any { it.title == item.title }
            if (isPresent)
                throw ElementAlreadyPresentException(
                    "Фильм с названием ${item.title} уже содержится в базе")

            data.add(FilmEntity(UUID.randomUUID(), item.title, item.actors))
        }
    }

    override fun update(id: UUID, item: FilmUpdateEntity) {
        loadDataDoActionAndSave { data ->
            val oldFilm = data.find { it.id == id }
                ?: throw IdNotPresentException("Нет фильма с таким $id")
            data.remove(oldFilm)

            val newTitle = item.title ?: oldFilm.title
            val newActors = item.actors ?: oldFilm.actors

            data.add(oldFilm.copy(title = newTitle, actors = newActors))
        }
    }
    override fun serialize(data: List<FilmEntity>): String {
        return json.encodeToString(data)
    }

    override fun deserialize(data: String): List<FilmEntity> {
        return json.decodeFromString(data)
    }
}