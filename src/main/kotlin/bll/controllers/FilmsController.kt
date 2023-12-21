package bll.controllers

import pll.models.input.FilmData
import pll.models.output.FilmOutput

interface FilmsController {
    fun getFilms(): List<FilmOutput>
    fun addFilm(newFilmData: FilmData)
    fun deleteFilm(filmId: Int)
    fun editFilm(filmData: FilmData, filmId: Int)

}