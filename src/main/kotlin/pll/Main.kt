package pll

import di.Di

fun main() {
    runRegistration()
}

private fun runRegistration() {
    while (true) {
        try {
            Di.registrationMenu.show()
            if (!Di.registrationMenu.processInputIfNotExit())
                break

            runApp()
        } catch (e: Exception) {
            println("Произошла ошибка: ${e.message}")
        }
    }
}

private fun runApp() {
    while (true) {
        try {
            Di.mainMenu.show()
            if (!Di.mainMenu.processInputIfNotExit())
                break
        } catch (e: Exception) {
            println("Произошла ошибка: ${e.message}")
        }
    }
}