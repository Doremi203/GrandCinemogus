package pll.models.output

data class FilmOutput(
    val id: Int,
    val title: String,
    val actors: MutableList<String>,
)
