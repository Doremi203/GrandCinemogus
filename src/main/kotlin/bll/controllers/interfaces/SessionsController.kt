package bll.controllers.interfaces

import pll.models.input.SessionData

interface SessionsController {
    fun addSession(newSessionData: SessionData)
}