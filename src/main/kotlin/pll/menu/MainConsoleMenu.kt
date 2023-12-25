package pll.menu

import bll.controllers.interfaces.FilmValidator
import bll.services.FilmIdService
import dal.repositories.interfaces.FilmsRepository
import dal.repositories.interfaces.SessionsRepository
import di.Di
import pll.InputReader
import pll.exceptions.NoSessionsException

class MainConsoleMenu(
    private val filmsMenu: FilmsConsoleMenu,
    private val ticketsMenu: TicketsConsoleMenu,
    private val filmValidator: FilmValidator,
    private val filmIdService: FilmIdService,
    private val inputReader: InputReader
) : ConsoleMenu("Главное меню") {

    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Меню фильмов", ::processFilmsMenu),
        MenuItem("Меню сеансов", ::processSessionsMenu),
        MenuItem("Меню билетов", ::processTicketsMenu),
        MenuItem("Пометить посетителя", ::tagVisitor)
    )

    private fun processFilmsMenu() {
        filmsMenu.handle()
    }

    private fun processSessionsMenu() {
        filmsMenu.showAllFilms()

        println("Введите название фильма для которого нужно открыть меню сеансов:")
        val title = inputReader.readStringUntilNotNull()
        filmValidator.validateTitle(title)

        val sessionsMenu = SessionsConsoleMenu(
            Di.sessionsRepository,
            Di.sessionsController,
            inputReader,
            filmIdService.getIdFromTitle(title)
        )
        sessionsMenu.handle()
    }

    private fun processTicketsMenu() {
        ticketsMenu.handle()
    }

    private fun tagVisitor() {
        TODO("Not yet implemented")
    }
}