package bll.controllers

import bll.controllers.interfaces.FilmValidator

class DefaultFilmValidator : FilmValidator {
    override fun validateTitle(title: String) {
        if (title.isEmpty())
            throw IllegalArgumentException("Название фильма не может быть пустым")
    }

     override fun validateActors(actors: List<String>) {
         if (actors.isEmpty())
                throw IllegalArgumentException("Список актеров не может быть пустыми")
     }
}