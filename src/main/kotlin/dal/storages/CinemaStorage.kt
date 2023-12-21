package dal.storages

import dal.entities.CinemaEntity

data class CinemaStorage(
    override val data: List<CinemaEntity>
) : Storage<CinemaEntity>()
