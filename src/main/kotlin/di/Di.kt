package di

import bll.controllers.DefaultFilmValidator
import bll.controllers.DefaultFilmsController
import bll.controllers.DefaultSessionsController
import bll.controllers.interfaces.FilmValidator
import bll.controllers.interfaces.FilmsController
import bll.controllers.interfaces.SessionsController
import bll.services.DefaultFilmIdService
import bll.services.FilmIdService
import dal.entities.CinemaEntity
import dal.entities.SessionEntity
import dal.repositories.CinemaJsonRepository
import dal.repositories.FilmsJsonRepository
import dal.repositories.JsonRepository
import dal.repositories.SessionsJsonRepository
import dal.repositories.interfaces.CinemaRepository
import dal.repositories.interfaces.FilmsRepository
import dal.repositories.interfaces.SessionsRepository
import pll.ConsoleInputReader
import pll.InputReader
import pll.menu.*

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

    val filmIdService: FilmIdService
        get() = DefaultFilmIdService(filmsRepository)

    val filmValidator: FilmValidator
        get() = DefaultFilmValidator()

    val inputReader: InputReader
        get() = ConsoleInputReader()

    val mainMenu: MainConsoleMenu
        get() = MainConsoleMenu(
            filmsMenu,
            ticketsMenu,
            filmValidator,
            filmIdService,
            inputReader
        )
    val filmsMenu: FilmsConsoleMenu
        get() = FilmsConsoleMenu(
            filmsRepository,
            filmsController,
            filmIdService,
            inputReader,
            filmEditMenu
        )

    val filmEditMenu: FilmEditConsoleMenu
        get() = FilmEditConsoleMenu()


    val ticketsMenu: TicketsConsoleMenu
        get() = TicketsConsoleMenu()
}