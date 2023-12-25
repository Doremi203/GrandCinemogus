package bll.controllers.interfaces

interface RegistrationValidator {
    fun validateLogin(login: String)
    fun validatePassword(password: String)
}