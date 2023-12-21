package bll.models

data class Film(
    val id: Int,
    val title: String,
    val actors: MutableList<String>,
)