package di

import bll.controllers.*
import bll.controllers.interfaces.*
import bll.services.*
import dal.repositories.CinemaJsonRepository
import dal.repositories.FilmsJsonRepository
import dal.repositories.SessionsJsonRepository
import dal.repositories.UsersJsonRepository
import dal.repositories.interfaces.CinemaRepository
import dal.repositories.interfaces.FilmsRepository
import dal.repositories.interfaces.SessionsRepository
import dal.repositories.interfaces.UsersRepository
import pll.ConsoleInputReader
import pll.InputReader
import pll.menu.FilmEditConsoleMenu
import pll.menu.FilmsConsoleMenu
import pll.menu.MainConsoleMenu
import pll.menu.RegistrationConsoleMenu

object Di {
    val configReader = ConfigReader("src/main/config/config.json")

    val filmsRepository: FilmsRepository
        get() = FilmsJsonRepository(configReader.config.filmsJsonPath)

    val sessionsRepository: SessionsRepository
        get() = SessionsJsonRepository(configReader.config.sessionsJsonPath)

    val cinemaRepository: CinemaRepository
        get() = CinemaJsonRepository(configReader.config.cinemaJsonPath)

    val usersRepository: UsersRepository
        get() = UsersJsonRepository(configReader.config.usersJsonPath)

    val sessionsController: SessionsController
        get() = DefaultSessionsController(
            sessionsRepository,
            cinemaRepository,
            configReader.config.cinemaId
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

    val registrationService: RegistrationService
        get() = DefaultRegistrationService(
            usersRepository
        )

    val ticketsService: TicketService
        get() = DefaultTicketService(
            sessionsRepository
        )

    val filmIdService: FilmIdService
        get() = DefaultFilmIdService(filmsRepository)

    val filmValidator: FilmValidator
        get() = DefaultFilmValidator()

    val registrationValidator: RegistrationValidator
        get() = DefaultRegistrationValidator()

    val inputReader: InputReader
        get() = ConsoleInputReader()

    val registrationMenu: RegistrationConsoleMenu
        get() = RegistrationConsoleMenu(
            registrationService,
            registrationValidator,
            inputReader
        )

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
        get() = FilmEditConsoleMenu(
            filmsRepository,
            filmIdService,
            filmValidator,
            inputReader
        )

}