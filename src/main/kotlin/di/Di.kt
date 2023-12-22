package di

import bll.controllers.DefaultFilmsController
import bll.controllers.DefaultFilmValidator
import bll.controllers.interfaces.FilmValidator
import bll.controllers.interfaces.FilmsController
import dal.entities.CinemaEntity
import dal.entities.FilmEntity
import dal.entities.SessionEntity
import dal.repositories.CinemaJsonRepository
import dal.repositories.FilmsJsonRepository
import dal.repositories.JsonRepository
import dal.repositories.SessionsJsonRepository
import pll.ConsoleInputReader
import pll.InputReader
import pll.menu.*

object Di {
    private val filmsRepository: JsonRepository<FilmEntity>
        get() = FilmsJsonRepository("src/main/resources/films.json")
    private val sessionsRepository: JsonRepository<SessionEntity>
        get() = SessionsJsonRepository("src/main/resources/sessions.json")
    private val cinemaRepository: JsonRepository<CinemaEntity>
        get() = CinemaJsonRepository("src/main/resources/cinema.json")

    private val filmValidator: FilmValidator
        get() = DefaultFilmValidator(filmsRepository)

    val filmsController: FilmsController
        get() = DefaultFilmsController(filmsRepository, filmValidator)

    val inputReader: InputReader
        get() = ConsoleInputReader()

    val mainMenu: Menu
        get() = ConsoleMenu("Главное меню",
            listOf(
                ConsoleMenu.MenuItem("Показать меню фильмов", ::processFilmsMenu),
                ConsoleMenu.MenuItem("Показать меню сеансов", ::processSessionsMenu),
                ConsoleMenu.MenuItem("Продать билет", ::processTicketsMenu),
                ConsoleMenu.MenuItem("Отметить посетителя", ::tagVisitor),
            )
        )
    val filmsMenu: Menu
        get() = ConsoleMenu("Меню фильмов",
            listOf(
                ConsoleMenu.MenuItem("Показать список фильмов", ::showAllFilms),
                ConsoleMenu.MenuItem("Добавить фильм", ::addFilm),
                ConsoleMenu.MenuItem("Удалить фильм", ::deleteFilm),
                ConsoleMenu.MenuItem("Редактировать фильм", ::processEditFilmMenu),
            )
        )

    val filmEditMenu: Menu
        get() = ConsoleMenu("Меню редактирования фильмов",
            listOf(
                ConsoleMenu.MenuItem("Изменить название", ::editFilmTitle),
                ConsoleMenu.MenuItem("Изменить актеров", ::editFilmActors),
            )
        )

    val sessionsMenu: Menu
        get() = ConsoleMenu("Меню сеансов",
            listOf(
                ConsoleMenu.MenuItem("Показать список сеансов", ::showSessions),
                ConsoleMenu.MenuItem("Выбрать сеанс", ::addSession),
                ConsoleMenu.MenuItem("Создать сеанс", ::deleteSession),
                ConsoleMenu.MenuItem("Удалить сеанс", ::editSession),
            )
        )

    val ticketsMenu: Menu
        get() = ConsoleMenu("Меню билетов",
            listOf(
                ConsoleMenu.MenuItem("Продать билет", ::sellTicket),
                ConsoleMenu.MenuItem("Вернуть билет", ::returnTicket),
            )
        )
}