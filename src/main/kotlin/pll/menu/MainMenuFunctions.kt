package pll.menu

import pll.*

fun showFilmsMenu() {
    println("Выберите действие:")
    println("1. Показать список фильмов")
    println("2. Добавить фильм")
    println("3. Удалить фильм")
    println("4. Редактировать фильм")
    println("0. Назад")

    val action = readlnOrNull()?.toIntOrNull()

    when (action) {
        1 -> showFilms()
        2 -> addFilm()
        3 -> deleteFilm()
        4 -> editFilm()
        0 -> return
        else -> println("Неверный ввод")
    }
}

fun showSessionsMenu() {
    println("Выберите действие:")
    println("1. Показать список сеансов")
    println("2. Выбрать сеанс")
    println("3. Создать сеанс")
    println("4. Удалить сеанс")
    println("0. Назад")

    val action = readlnOrNull()?.toIntOrNull()

    when (action) {
        1 -> showSessions()
        2 -> addSession()
        3 -> deleteSession()
        4 -> editSession()
        0 -> return
        else -> println("Неверный ввод")
    }
}

fun showTicketsMenu() {
    println("1. Продать билет")
    println("2. Вернуть билет")

    val action = readlnOrNull()?.toIntOrNull()

    when (action) {
        1 -> sellTicket()
        2 -> returnTicket()
        else -> println("Неверный ввод")
    }
}

fun tagVisitor() {
    TODO("Not yet implemented")
}