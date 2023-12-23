package bll.services

import java.util.*

interface FilmIdService {
    fun getFilmIdFromTitle(title: String): UUID
}