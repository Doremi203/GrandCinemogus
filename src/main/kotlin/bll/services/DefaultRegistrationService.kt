package bll.services

import bll.exceptions.UserAlreadyRegisteredException
import bll.services.interfaces.RegistrationService
import dal.entities.UserAddEntity
import dal.repositories.interfaces.UsersRepository
import java.security.MessageDigest

class DefaultRegistrationService(
    private val usersRepository: UsersRepository
) : RegistrationService {
    override fun registerUser(login: String, password: String) {
        val users = usersRepository.getAll()
        if (users.any { it.login == login })
            throw UserAlreadyRegisteredException("Пользователь с таким логином уже существует")

        val hashedPassword = hashPassword(password)

        usersRepository.add(
            UserAddEntity(
                login = login,
                password = hashedPassword
            )
        )
    }

    override fun login(login: String, password: String) {
        val users = usersRepository.getAll()
        val hashedPassword = hashPassword(password)

        users.find { it.login == login && it.password == hashedPassword }
            ?: throw IllegalArgumentException("Неверный логин или пароль")
    }

    private fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}