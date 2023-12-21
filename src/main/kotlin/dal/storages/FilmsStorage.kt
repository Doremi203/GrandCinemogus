package dal.storages

import dal.entities.FilmEntity
import kotlinx.serialization.Serializable

@Serializable
data class FilmsStorage(
    override val data: List<FilmEntity>
) : Storage<FilmEntity>()
