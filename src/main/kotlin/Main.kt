import kotlin.system.exitProcess

fun main() {
    while (true) {
        println("Выберите действие:")
        println("1. Показать меню фильмов")
        println("2. Показать меню сеансов")
        println("3. Продать билет")
        println("4. Вернуть билет")
        println("0. Выход")

        val action = readlnOrNull()?.toIntOrNull()

        when (action) {
            1 -> showFilmsMenu()
            2 -> showSessionsMenu()
            3 -> ticketsMenu()
            4 -> tagVisitor()
            0 -> exitProcess(0)
            else -> println("Неверный ввод")
        }
    }
}

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

fun showFilms() {
    TODO("Not yet implemented")
}

fun addFilm() {
    TODO("Not yet implemented")
}

fun deleteFilm() {
    TODO("Not yet implemented")
}

fun editFilm() {
    TODO("Not yet implemented")
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

fun showSessions() {
    TODO("Not yet implemented")
}

fun addSession() {
    TODO("Not yet implemented")
}

fun deleteSession() {
    TODO("Not yet implemented")
}

fun editSession() {
    TODO("Not yet implemented")
}

fun ticketsMenu() {
    println("1. Продать билет")
    println("2. Вернуть билет")

    val action = readlnOrNull()?.toIntOrNull()

    when (action) {
        1 -> sellTicket()
        2 -> returnTicket()
        else -> println("Неверный ввод")
    }
}

fun sellTicket() {
    TODO("Not yet implemented")
}

fun returnTicket() {
    TODO("Not yet implemented")
}

fun tagVisitor() {
    TODO("Not yet implemented")
}