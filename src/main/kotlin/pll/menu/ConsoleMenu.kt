package pll.menu

class ConsoleMenu(
    private val name: String,
    private val menuItems: List<MenuItem>,
) : Menu {
    data class MenuItem (
        val title: String,
        val action: () -> Unit,
    )

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

    override fun handle() {
        do {
            var isExit = false
            try {
                show()
                isExit = !processInputIfNotExit()
            } catch (e: Exception) {
                println("Произошла ошибка: ${e.message}")
            }
        } while(!isExit)
    }
}