package di

import bll.controllers.DefaultFilmValidator
import bll.controllers.interfaces.FilmValidator
import bll.services.DefaultFilmIdService
import bll.services.FilmIdService
import dal.entities.CinemaEntity
import dal.entities.SessionEntity
import dal.repositories.*
import pll.ConsoleInputReader
import pll.InputReader
import pll.menu.*

object Di {
    val filmsRepository: FilmsRepository
        get() = FilmsJsonRepository("src/main/resources/films.json")
    val sessionsRepository: JsonRepository<SessionEntity>
        get() = SessionsJsonRepository("src/main/resources/sessions.json")
    val cinemaRepository: JsonRepository<CinemaEntity>
        get() = CinemaJsonRepository("src/main/resources/cinema.json")

    val filmIdService: FilmIdService
        get() = DefaultFilmIdService(filmsRepository)

    val filmValidator: FilmValidator
        get() = DefaultFilmValidator()

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