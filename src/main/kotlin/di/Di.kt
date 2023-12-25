package di

import bll.controllers.DefaultFilmsController
import bll.controllers.DefaultSessionsController
import bll.controllers.DefaultTicketsController
import bll.controllers.interfaces.FilmsController
import bll.controllers.interfaces.SessionsController
import bll.controllers.interfaces.TicketsController
import bll.services.DefaultFilmIdService
import bll.services.DefaultRegistrationService
import bll.services.DefaultTicketService
import bll.services.interfaces.FilmIdService
import bll.services.interfaces.RegistrationService
import bll.services.interfaces.TicketService
import bll.validators.DefaultFilmValidator
import bll.validators.DefaultRegistrationValidator
import bll.validators.interfaces.FilmValidator
import bll.validators.interfaces.RegistrationValidator
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
    private val configReader = ConfigReader("src/main/config/config.json")

    private val filmsRepository: FilmsRepository
        get() = FilmsJsonRepository(configReader.config.filmsJsonPath)

    val sessionsRepository: SessionsRepository
        get() = SessionsJsonRepository(configReader.config.sessionsJsonPath)

    private val cinemaRepository: CinemaRepository
        get() = CinemaJsonRepository(configReader.config.cinemaJsonPath)

    private val usersRepository: UsersRepository
        get() = UsersJsonRepository(configReader.config.usersJsonPath)

    val sessionsController: SessionsController
        get() = DefaultSessionsController(
            sessionsRepository,
            cinemaRepository,
            configReader.config.cinemaId
        )

    private val filmsController: FilmsController
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

    private val registrationService: RegistrationService
        get() = DefaultRegistrationService(
            usersRepository
        )

    private val ticketsService: TicketService
        get() = DefaultTicketService()

    private val filmIdService: FilmIdService
        get() = DefaultFilmIdService(filmsRepository)

    private val filmValidator: FilmValidator
        get() = DefaultFilmValidator()

    private val registrationValidator: RegistrationValidator
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
    private val filmsMenu: FilmsConsoleMenu
        get() = FilmsConsoleMenu(
            filmsRepository,
            filmsController,
            filmValidator,
            inputReader,
            filmEditMenu
        )

    private val filmEditMenu: FilmEditConsoleMenu
        get() = FilmEditConsoleMenu(
            filmsRepository,
            filmIdService,
            filmValidator,
            inputReader
        )

}