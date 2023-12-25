package dal.entities

data class FilmUpdateEntity(
    val title: String? = null,
    val actors: List<String>? = null,
    val durationInMinutes: Int? = null
)
