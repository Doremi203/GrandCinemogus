package pll.menu

import di.Di

fun processFilmsMenu() {
    Di.filmsMenu.handle()
}

fun processSessionsMenu() {
    Di.sessionsMenu.handle()
}

fun processTicketsMenu() {
    Di.ticketsMenu.handle()
}

fun tagVisitor() {
    TODO("Not yet implemented")
}