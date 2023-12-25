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
        if (actors.any { it.isEmpty() })
            throw IllegalArgumentException("Актеры не могут быть пустыми")
    }

    override fun validateDuration(duration: Int) {
        if (duration <= 0)
            throw IllegalArgumentException("Длительность фильма должна быть больше 0")
    }
}