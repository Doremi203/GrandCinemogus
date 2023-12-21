package dal.storages

import dal.entities.FilmEntity

data class FilmsStorage(
    override val data: List<FilmEntity>
) : Storage<FilmEntity>()
