package pll

import di.Di

fun main() {
    while (true) {
        try {
            Di.registrationMenu.show()
            if (Di.registrationMenu.processInputIfNotExit())
                break

            Di.mainMenu.show()
            if (!Di.mainMenu.processInputIfNotExit())
                break
        } catch (e: Exception) {
            println("Произошла ошибка: ${e.message}")
        }
    }
}