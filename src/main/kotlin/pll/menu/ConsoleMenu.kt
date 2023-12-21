package pll.menu

class ConsoleMenu(
    private val menuItems: List<MenuItem>,
) : Menu {
    data class MenuItem (
        val title: String,
        val action: () -> Unit,
    )

    override fun show() {
        println("Menu:")
        menuItems.forEachIndexed { index, item ->
            println("${index + 1}. ${item.title}")
        }
    }

    override fun processInput() {
        val input = readlnOrNull()?.toIntOrNull()
        if (input == null || input !in menuItems.indices) {
            println("Invalid input")
            return
        }

        menuItems[input].action()
    }
}