package pll.menu

import bll.controllers.interfaces.RegistrationValidator
import bll.exceptions.UserAlreadyRegisteredException
import bll.services.RegistrationService
import pll.InputReader

class RegistrationConsoleMenu(
    private val registrationService: RegistrationService,
    private val registrationValidator: RegistrationValidator,
    private val inputReader: InputReader
) : ConsoleMenu("Меню регистрации") {
    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Зарегистрировать пользователя", ::processRegistration),
        MenuItem("Войти в систему", ::processLogin),
    )

    private fun processRegistration() {
        processOperationWithLoginAndPassword { login, password ->
            registrationService.registerUser(login, password)
        }
    }

    private fun processLogin() {
        processOperationWithLoginAndPassword { login, password ->
            registrationService.login(login, password)
        }
    }

    private fun processOperationWithLoginAndPassword(
        operation: (String, String) -> Unit
    ) {
        handleExceptions {
            println("Введите логин:")
            val login = inputReader.readStringUntilNotNull()
            registrationValidator.validateLogin(login)

            println("Введите пароль:")
            val password = inputReader.readStringUntilNotNull()
            registrationValidator.validatePassword(password)

            operation(login, password)
        }
    }

    private fun handleExceptions(block: () -> Unit) {
        while (true) {
            try {
                block()
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: UserAlreadyRegisteredException) {
                println(e.message)
            } catch (e: NoSuchElementException) {
                println(e.message)
            }
        }
    }
}