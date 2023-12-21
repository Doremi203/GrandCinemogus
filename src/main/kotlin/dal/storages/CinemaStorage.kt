package dal.storages

import dal.entities.CinemaEntity
import kotlinx.serialization.Serializable

@Serializable
data class CinemaStorage(
    override val data: List<CinemaEntity>
) : Storage<CinemaEntity>()
