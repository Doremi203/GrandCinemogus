package pll.models.output

data class SessionOutput(
    val id: Int,
    val filmId: Int,
    val dateTime: String,
    val ticketPrice: Double,
    val seats: List<List<SeatStateOutput>>,
)
