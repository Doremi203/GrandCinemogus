package bll.controllers.interfaces

import pll.models.input.FilmData
import pll.models.output.FilmOutput

interface FilmsController {
    fun getAllFilms(): List<FilmOutput>
    fun getFilmById(filmId: Int): FilmOutput
    fun addFilm(newFilmData: FilmData): Result<String>
    fun deleteFilm(filmId: Int): Result<String>
    fun editFilmTitle(filmId: Int, newTitle: String): Result<String>
    fun editFilmActors(filmId: Int, newActors: List<String>): Result<String>
}