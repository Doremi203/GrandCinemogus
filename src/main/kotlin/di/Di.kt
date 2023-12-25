package di

import bll.controllers.DefaultFilmValidator
import bll.controllers.DefaultFilmsController
import bll.controllers.DefaultSessionsController
import bll.controllers.DefaultTicketsController
import bll.controllers.interfaces.FilmValidator
import bll.controllers.interfaces.FilmsController
import bll.controllers.interfaces.SessionsController
import bll.controllers.interfaces.TicketsController
import bll.services.DefaultFilmIdService
import bll.services.DefaultTicketService
import bll.services.FilmIdService
import bll.services.TicketService
import dal.repositories.CinemaJsonRepository
import dal.repositories.FilmsJsonRepository
import dal.repositories.SessionsJsonRepository
import dal.repositories.interfaces.CinemaRepository
import dal.repositories.interfaces.FilmsRepository
import dal.repositories.interfaces.SessionsRepository
import pll.ConsoleInputReader
import pll.InputReader
import pll.menu.FilmEditConsoleMenu
import pll.menu.FilmsConsoleMenu
import pll.menu.MainConsoleMenu

object Di {
    val filmsRepository: FilmsRepository
        get() = FilmsJsonRepository("src/main/resources/films.json")
    val sessionsRepository: SessionsRepository
        get() = SessionsJsonRepository("src/main/resources/sessions.json")
    val cinemaRepository: CinemaRepository
        get() = CinemaJsonRepository("src/main/resources/cinema.json")

    val sessionsController: SessionsController
        get() = DefaultSessionsController(
            sessionsRepository,
            cinemaRepository,
            cinemaRepository.getAll()[0].id
        )

    val filmsController: FilmsController
        get() = DefaultFilmsController(
            sessionsRepository,
            filmsRepository,
            filmIdService
        )

    val ticketsController: TicketsController
        get() = DefaultTicketsController(
            sessionsRepository,
            ticketsService
        )

    val ticketsService: TicketService
        get() = DefaultTicketService(
            sessionsRepository
        )

    val filmIdService: FilmIdService
        get() = DefaultFilmIdService(filmsRepository)

    val filmValidator: FilmValidator
        get() = DefaultFilmValidator()

    val inputReader: InputReader
        get() = ConsoleInputReader()

    val mainMenu: MainConsoleMenu
        get() = MainConsoleMenu(
            filmsMenu,
            filmValidator,
            filmIdService,
            inputReader
        )
    val filmsMenu: FilmsConsoleMenu
        get() = FilmsConsoleMenu(
            filmsRepository,
            filmsController,
            filmValidator,
            inputReader,
            filmEditMenu
        )

    val filmEditMenu: FilmEditConsoleMenu
        get() = FilmEditConsoleMenu()

}