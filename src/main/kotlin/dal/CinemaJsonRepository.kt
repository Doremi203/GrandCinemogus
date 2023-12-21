package dal

import dal.entities.CinemaEntity
import dal.storages.Storage

class CinemaJsonRepository(path: String) : BaseJsonRepository<CinemaEntity>(path) {
    override fun getAll(): List<CinemaEntity> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): CinemaEntity? {
        TODO("Not yet implemented")
    }

    override fun delete(item: CinemaEntity) {
        TODO("Not yet implemented")
    }

    override fun update(item: CinemaEntity) {
        TODO("Not yet implemented")
    }

    override fun add(item: CinemaEntity) {
        TODO("Not yet implemented")
    }

    override fun loadFromFile(): Storage<CinemaEntity> {
        TODO("Not yet implemented")
    }

    override fun saveToFile(data: Storage<CinemaEntity>) {
        TODO("Not yet implemented")
    }

}