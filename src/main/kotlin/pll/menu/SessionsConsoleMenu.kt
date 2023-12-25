package pll.menu

import bll.controllers.interfaces.SessionsController
import bll.exceptions.TicketAlreadySoldException
import bll.exceptions.TicketAlreadyUsedException
import bll.exceptions.TicketNotSoldException
import dal.entities.SeatStateEntity
import dal.entities.SessionEntity
import dal.repositories.interfaces.SessionsRepository
import di.Di
import kotlinx.datetime.LocalDateTime
import pll.InputReader
import pll.exceptions.NoFilmsException
import pll.exceptions.NoSessionsException
import pll.models.input.SessionData
import java.util.*

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
        MenuItem("Купить билеты", ::buyTickets),
        MenuItem("Вернуть билеты", ::returnTickets),
        MenuItem("Отметить посетителей", ::tagVisitors),
        MenuItem("Посмотреть схему зала", ::showHallForSession)
    )

    private fun showAllSessionsForFilm() {
        val sessions = sessionsRepository.getAllByFilmId(filmId)
        if (sessions.isEmpty())
            throw NoSessionsException("Список сеансов для выбранного фильма пуст")

        println("Список сеансов:")
        sessions.forEachIndexed { id, session ->
            println("${id + 1}. Дата: ${session.dateTime} Цена билета: ${session.ticketPrice}")
        }
    }

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
        var dateTime: LocalDateTime?
        while (true) {
            try {
                println("Введите дату и время сеанса в формате dd.MM.yyyy HH:mm:")
                dateTime = inputReader.readDateTimeUntilNotNull()
                break
            } catch (e: IllegalArgumentException) {
                println("Некорректное значение для даты и времени")
            }
        }
        // Can't be null because of while loop
        return dateTime!!
    }

    private fun deleteSession() {
        handleExceptions {
            showAllSessionsForFilm()
            println("Выберите сеанс для удаления (Id)")

            val sessionToDelete = getSessionBySequentialId()

            sessionsRepository.delete(sessionToDelete.id)

            println(
                "Сеанс на фильм успешно удален на время " +
                        "${sessionToDelete.dateTime.date} ${sessionToDelete.dateTime.time}"
            )
        }
    }

    private fun getSessionBySequentialId(): SessionEntity {
        val sessions = sessionsRepository.getAllByFilmId(filmId)

        val id = inputReader.readIntUntilNotNull()
        if (id < 1 || id > sessions.size)
            throw IllegalArgumentException("Некорректное значение id")

        val sessionToDelete = sessions[id - 1]
        return sessionToDelete
    }

    private fun buyTickets() {
        handleExceptions {
            showAllSessionsForFilm()
            println("Выберите сеанс для покупки билета (Id)")

            val sessionToSell = getSessionBySequentialId()

            showSessionSeats(sessionToSell)

            val count = readNumberOfTicketsToSell()
            val ticketsToBuy = readTicketsInfo(count, sessionToSell)

            ticketsToBuy.forEach { ticketToBuy ->
                buyTicket(sessionToSell, ticketToBuy)
            }
        }
    }

    private fun readNumberOfTicketsToSell(): Int {
        println("Введите количество билетов для покупки:")

        val count = Di.inputReader.readIntUntilNotNull()
        if (count < 1)
            throw IllegalArgumentException("Нельзя купить меньше одного билета")

        return count
    }

    private fun buyTicket(sessionToSell: SessionEntity, ticketToBuy: Pair<Int, Int>) {
        try {
            Di.ticketsController.sellTicket(
                sessionId = sessionToSell.id,
                rowNumber = ticketToBuy.first,
                seatNumber = ticketToBuy.second
            )

            println(
                "Билет на место (${ticketToBuy.first}, " +
                        "${ticketToBuy.second}) успешно куплен"
            )
        } catch (e: TicketAlreadySoldException) {
            println(e.message)
        }
    }

    private fun returnTickets() {
        handleExceptions {
            showAllSessionsForFilm()
            println("Выберите сеанс для возврата билета (Id)")

            val sessionToReturn = getSessionBySequentialId()
            val count = readNumberOfTicketsToReturn()
            val ticketsToReturn = readTicketsInfo(count, sessionToReturn)

            ticketsToReturn.forEach { ticketToReturn ->
                returnTicket(sessionToReturn, ticketToReturn)
            }
        }
    }

    private fun readNumberOfTicketsToReturn(): Int {
        println("Введите количество билетов для возврата:")

        val count = Di.inputReader.readIntUntilNotNull()
        if (count < 1)
            throw IllegalArgumentException("Нельзя вернуть меньше одного билета")

        return count
    }

    private fun returnTicket(
        sessionToReturn: SessionEntity,
        ticketToReturn: Pair<Int, Int>
    ) {
        try {
            Di.ticketsController.returnTicket(
                sessionId = sessionToReturn.id,
                rowNumber = ticketToReturn.first,
                seatNumber = ticketToReturn.second
            )
            println(
                "Билет на место (${ticketToReturn.first}, " +
                        "${ticketToReturn.second}) успешно возвращен"
            )
        } catch (e: TicketNotSoldException) {
            println(e.message)
        }
    }

    private fun tagVisitors() {
        handleExceptions {
            showAllSessionsForFilm()
            println("Выберите сеанс для отметки посетителей (Id)")

            val sessionToTag = getSessionBySequentialId()

            showSessionSeats(sessionToTag)

            val count = readNumberOfVisitors()
            val ticketsToTag = readTicketsInfo(count, sessionToTag)

            ticketsToTag.forEach { ticketToTag ->
                tagVisitor(sessionToTag, ticketToTag)
            }
        }
    }

    private fun readNumberOfVisitors(): Int {
        println("Введите количество посетителей:")

        val count = Di.inputReader.readIntUntilNotNull()
        if (count < 1)
            throw IllegalArgumentException("Нельзя отметить меньше одного посетителя")

        return count
    }

    private fun tagVisitor(sessionToTag: SessionEntity, ticketToTag: Pair<Int, Int>) {
        try {
            Di.ticketsController.tagTicketAsVisited(
                sessionId = sessionToTag.id,
                rowNumber = ticketToTag.first,
                seatNumber = ticketToTag.second
            )

            println(
                "Билет на место (${ticketToTag.first}, " +
                        "${ticketToTag.second}) успешно отмечен"
            )
        } catch (e: TicketNotSoldException) {
            println(e.message)
        } catch (e: TicketAlreadyUsedException) {
            println(e.message)
        }
    }

    private fun readTicketsInfo(
        count: Int,
        sessionToBuy: SessionEntity
    ): List<Pair<Int, Int>> {
        val ticketsToBuy = mutableListOf<Pair<Int, Int>>()
        val rowCount = sessionToBuy.seats.size

        for (number in 1..count) {
            println("Введите информацию о билете №$number")

            val row = readRowForTicket(rowCount)
            val seat = readSeatNumberForTicket(sessionToBuy, row)

            ticketsToBuy.add(Pair(row, seat))
        }
        return ticketsToBuy
    }

    private fun readRowForTicket(rowCount: Int): Int {
        var row = 0
        handleExceptions {
            println("Введите ряд для билета:")
            row = Di.inputReader.readIntUntilNotNull()
            if (row < 1 || row > rowCount)
                throw IllegalArgumentException("Некорректное значение ряда")
        }
        return row
    }

    private fun readSeatNumberForTicket(sessionToBuy: SessionEntity, row: Int): Int {
        var seat = 0
        handleExceptions {
            println("Введите номер места для билета:")
            seat = Di.inputReader.readIntUntilNotNull()
            if (seat < 1 || seat > sessionToBuy.seats[row - 1].size)
                throw IllegalArgumentException("Некорректное значение места")
        }
        return seat
    }

    private fun showHallForSession() {
        handleExceptions {
            showAllSessionsForFilm()
            println("Выберите сеанс для просмотра схемы зала (Id)")

            val sessionToSell = getSessionBySequentialId()

            showSessionSeats(sessionToSell)
        }
    }

    private fun showSessionSeats(sessionToSell: SessionEntity) {
        println("Схема зала:")
        sessionToSell.seats.forEachIndexed { rowNumber, row ->
            print("${rowNumber + 1}: ")
            row.forEachIndexed { seatNumber, seat ->
                print(
                    when (seat) {
                        SeatStateEntity.FREE -> "O"
                        SeatStateEntity.TAKEN -> "T"
                        SeatStateEntity.SOLD -> "S"
                    }
                )
                if (seatNumber != row.size - 1)
                    print(" ")
            }
            println()
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
            } catch (e: NoSessionsException) {
                println(e.message)
                break
            } catch (e: NoFilmsException) {
                println(e.message)
                break
            } catch (e: NumberFormatException) {
                println("Некорректное значение для чисел")
            }
        }
    }
}
