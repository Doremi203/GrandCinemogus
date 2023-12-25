package bll.controllers.interfaces

interface FilmValidator {
    fun validateTitle(title: String)
    fun validateActors(actors: List<String>)
    fun validateDuration(duration: Int)
}
