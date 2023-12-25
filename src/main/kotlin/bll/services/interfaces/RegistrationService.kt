package bll.services.interfaces

interface RegistrationService {
    fun registerUser(login: String, password: String)
    fun login(login: String, password: String)
}