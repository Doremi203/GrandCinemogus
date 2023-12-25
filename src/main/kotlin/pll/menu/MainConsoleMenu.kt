package pll.menu

import bll.controllers.interfaces.FilmValidator
import bll.services.FilmIdService
import di.Di
import pll.InputReader
import pll.exceptions.NoFilmsException
import pll.exceptions.NoSessionsException

class MainConsoleMenu(
    private val filmsMenu: FilmsConsoleMenu,
    private val filmValidator: FilmValidator,
    private val filmIdService: FilmIdService,
    private val inputReader: InputReader
) : ConsoleMenu("Главное меню") {

    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Меню фильмов", ::processFilmsMenu),
        MenuItem("Меню сеансов для фильма", ::processSessionsMenu),
    )

    private fun processFilmsMenu() {
        do {
            var isExit = false
            try {
                filmsMenu.show()
                isExit = !filmsMenu.processInputIfNotExit()
            } catch (e: NoFilmsException) {
                println(e.message)
            } catch (e: Exception) {
                println("Произошла ошибка: ${e.message}")
            }
        } while (!isExit)
    }

    private fun processSessionsMenu() {
        handleExceptions {
            val sessionsMenu = createSessionsConsoleMenuForFilm()
            do {
                var isExit = false
                try {
                    sessionsMenu.show()
                    isExit = !sessionsMenu.processInputIfNotExit()
                } catch (e: NoSessionsException) {
                    println(e.message)
                } catch (e: Exception) {
                    println("Произошла ошибка: ${e.message}")
                }
            } while (!isExit)
        }
    }

    private fun createSessionsConsoleMenuForFilm(): SessionsConsoleMenu {
        filmsMenu.showAllFilms()
        println("Введите название фильма для которого нужно открыть меню сеансов:")
        val title = inputReader.readStringUntilNotNull()
        filmValidator.validateTitle(title)

        val sessionsMenu = SessionsConsoleMenu(
            Di.sessionsRepository,
            Di.sessionsController,
            Di.inputReader,
            filmIdService.getIdFromTitle(title)
        )
        return sessionsMenu
    }

    private fun handleExceptions(block: () -> Unit) {
        while (true) {
            try {
                block()
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: NoSuchElementException) {
                println(e.message)
            } catch (e: NoFilmsException) {
                println(e.message)
                break
            } catch (e: NumberFormatException) {
                println("Некорректное значение для чисел")
            }
        }
    }
}