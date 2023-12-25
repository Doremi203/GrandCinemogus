package bll.services

interface RegistrationService {
    fun registerUser(login: String, password: String)
    fun login(login: String, password: String)
}