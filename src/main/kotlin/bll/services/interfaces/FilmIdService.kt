package bll.services.interfaces

import java.util.*

interface FilmIdService {
    fun getIdFromTitle(title: String): UUID
}