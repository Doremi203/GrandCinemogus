package pll.menu

sealed class ConsoleMenu(
    private val name: String,
) : Menu {
    data class MenuItem(
        val title: String,
        val action: () -> Unit,
    )

    abstract val menuItems: List<MenuItem>

    override fun show() {
        println(name)
        menuItems.forEachIndexed { index, item ->
            println("${index + 1}. ${item.title}")
        }
        println("0. Выход")
    }

    override fun processInputIfNotExit(): Boolean {
        val input = readlnOrNull()?.toIntOrNull()
        if (input == null || (input - 1 !in menuItems.indices && input != 0)) {
            println("Неверный ввод")
            return true
        }
        if (input == 0)
            return false

        menuItems[input - 1].action()
        return true
    }

}