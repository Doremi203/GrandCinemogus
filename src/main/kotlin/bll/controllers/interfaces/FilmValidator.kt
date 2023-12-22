package bll.controllers.interfaces

interface FilmValidator {
    fun validateTitle(title: String): Boolean
    fun validateActors(actors: List<String>): Boolean

}
