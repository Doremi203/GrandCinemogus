package bll.validators

import bll.validators.interfaces.RegistrationValidator

class DefaultRegistrationValidator : RegistrationValidator {
    override fun validateLogin(login: String) {
        if (login.isEmpty())
            throw IllegalArgumentException("Логин не может быть пустым")
    }

    override fun validatePassword(password: String) {
        if (password.isEmpty())
            throw IllegalArgumentException("Пароль не может быть пустым")
    }
}