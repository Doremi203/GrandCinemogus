package pll

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
}