package pll

import kotlinx.datetime.LocalDateTime

class ConsoleInputReader : InputReader {
    override fun readIntUntilNotNull(): Int {
        var input: Int? = null
        while (input == null) {
            input = readlnOrNull()?.toIntOrNull()
        }
        return input
    }

    override fun readStringUntilNotNull(): String {
        var input: String? = null
        while (input == null) {
            input = readlnOrNull()
        }
        return input
    }

    override fun readDateTimeUntilNotNull(): LocalDateTime {
        var input: String? = null
        while (input == null) {
            input = readlnOrNull()
        }
        val split = input.split(" ")
        if (split.size != 2)
            throw IllegalArgumentException("Неверный формат даты")

        val date = split[0].split(".").map { it.toInt() }
        val time = split[1].split(":").map { it.toInt() }
        if (date.size != 3 || time.size != 2)
            throw IllegalArgumentException("Неверный формат даты")
        return LocalDateTime(
            date[2],
            date[1],
            date[0],
            time[0],
            time[1]
        )
    }

    override fun readDoubleUntilNotNull(): Double {
        var input: Double? = null
        while (input == null) {
            input = readlnOrNull()?.toDoubleOrNull()
        }
        return input
    }
}