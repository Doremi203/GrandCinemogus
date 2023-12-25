package pll.menu

import bll.controllers.DefaultSessionsController
import bll.controllers.interfaces.SessionsController
import dal.entities.SessionAddEntity
import dal.repositories.interfaces.SessionsRepository
import di.Di
import kotlinx.datetime.LocalDateTime
import pll.InputReader
import pll.exceptions.NoFilmsException
import pll.exceptions.NoSessionsException
import pll.models.input.SessionData
import java.time.DateTimeException
import java.util.NoSuchElementException
import java.util.UUID

class SessionsConsoleMenu(
    private val sessionsRepository: SessionsRepository,
    private val sessionsController: SessionsController,
    private val inputReader: InputReader,
    private val filmId: UUID
) : ConsoleMenu(
    "Меню сеансов",
) {
    override val menuItems = listOf(
        MenuItem("Показать все сеансы", ::showAllSessionsForFilm),
        MenuItem("Добавить сеанс", ::addSession),
        MenuItem("Удалить сеанс", ::deleteSession),
    )

    private fun addSession() {
        handleExceptions {
            val dateTime = readSessionDateTime()

            println("Введите цену билета:")
            val ticketPrice = inputReader.readDoubleUntilNotNull()

            sessionsController.addSession(
                SessionData(
                    filmId = filmId,
                    dateTime = dateTime,
                    ticketPrice = ticketPrice
                )
            )

            println("Сеанс на фильм успешно добавлен на время ${dateTime.date} ${dateTime.time}")
        }
    }

    private fun readSessionDateTime(): LocalDateTime {
        var dateTime: LocalDateTime? = null
        while (true) {
            try {
                println("Введите дату и время сеанса в формате dd.MM.yyyy HH:mm:")
                dateTime = inputReader.readDateTimeUntilNotNull()
                break
            } catch (e: IllegalArgumentException) {
                println("Некорректное значение для даты и времени")
            }
        }
        return dateTime!!
    }

    private fun deleteSession() {
        handleExceptions {
            showAllSessionsForFilm()
            println("Выберите сеанс для удаления (Id)")

            val sessions = sessionsRepository.getAllByFilmId(filmId)

            val id = inputReader.readIntUntilNotNull()
            if (id < 1 || id > sessions.size)
                throw IllegalArgumentException("Некорректное значение id")

            val sessionToDelete = sessions[id - 1]

            sessionsRepository.delete(sessionToDelete.id)

            println("Сеанс на фильм успешно удален на время " +
                    "${sessionToDelete.dateTime.date} ${sessionToDelete.dateTime.time}")
        }
    }

    private fun showAllSessionsForFilm() {
        val sessions = sessionsRepository.getAllByFilmId(filmId)
        if (sessions.isEmpty())
            throw NoSessionsException("Список сеансов для выбранного фильма пуст")

        println("Список сеансов:")
        sessions.forEachIndexed { id, session ->
            println("${id + 1}. Дата: ${session.dateTime} Цена билета: ${session.ticketPrice}")
        }
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
