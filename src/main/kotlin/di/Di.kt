package di

import dal.entities.CinemaEntity
import dal.entities.FilmEntity
import dal.entities.SessionEntity
import dal.repositories.CinemaJsonRepository
import dal.repositories.FilmsJsonRepository
import dal.repositories.JsonRepository
import dal.repositories.SessionsJsonRepository
import pll.menu.*
import kotlin.system.exitProcess

object Di {
    val filmsRepository: JsonRepository<FilmEntity>
        get() = FilmsJsonRepository("src/main/resources/films.json")
    val sessionsRepository: JsonRepository<SessionEntity>
        get() = SessionsJsonRepository("src/main/resources/sessions.json")
    val cinemaRepository: JsonRepository<CinemaEntity>
        get() = CinemaJsonRepository("src/main/resources/cinema.json")

    val mainMenu: Menu
        get() = ConsoleMenu(
            listOf(
                ConsoleMenu.MenuItem("Показать меню фильмов", ::showFilmsMenu),
                ConsoleMenu.MenuItem("Показать меню сеансов", ::showSessionsMenu),
                ConsoleMenu.MenuItem("Продать билет", ::showTicketsMenu),
                ConsoleMenu.MenuItem("Вернуть билет", ::tagVisitor),
                ConsoleMenu.MenuItem("Выход") { exitProcess(0) },
            )
        )
    val filmsMenu: Menu
        get() = ConsoleMenu(
            listOf(
                ConsoleMenu.MenuItem("Показать список фильмов", ::showFilms),
                ConsoleMenu.MenuItem("Добавить фильм", ::addFilm),
                ConsoleMenu.MenuItem("Удалить фильм", ::deleteFilm),
                ConsoleMenu.MenuItem("Редактировать фильм", ::editFilm),
                ConsoleMenu.MenuItem("Назад") { },
            )
        )

    val sessionsMenu: Menu
        get() = ConsoleMenu(
            listOf(
                ConsoleMenu.MenuItem("Показать список сеансов", ::showSessions),
                ConsoleMenu.MenuItem("Выбрать сеанс", ::addSession),
                ConsoleMenu.MenuItem("Создать сеанс", ::deleteSession),
                ConsoleMenu.MenuItem("Удалить сеанс", ::editSession),
                ConsoleMenu.MenuItem("Назад") { },
            )
        )

    val ticketsMenu: Menu
        get() = ConsoleMenu(
            listOf(
                ConsoleMenu.MenuItem("Продать билет", ::sellTicket),
                ConsoleMenu.MenuItem("Вернуть билет", ::returnTicket),
                ConsoleMenu.MenuItem("Назад") { },
            )
        )
}