package bll.services

import java.util.*

interface FilmIdService {
    fun getIdFromTitle(title: String): UUID
}