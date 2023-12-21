package pll

import di.Di

fun main() {
    while (true) {
        Di.mainMenu.show()
        Di.mainMenu.processInput()
    }
}