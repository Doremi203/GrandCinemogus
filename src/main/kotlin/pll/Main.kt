package pll

import di.Di

fun main() {
    val mainMenu = Di.mainMenu
    do {
        var isExit = false
        try {
            mainMenu.show()
            isExit = !mainMenu.processInputIfNotExit()
        } catch (e: Exception) {
            println("Произошла ошибка: ${e.message}")
        }
    } while(!isExit)
}