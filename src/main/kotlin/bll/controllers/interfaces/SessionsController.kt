package bll.controllers.interfaces

import pll.models.input.SessionData
import pll.models.output.SessionOutput

interface SessionsController {
    fun getSessions(): List<SessionOutput>
    fun addSession(newSessionData: SessionData)
    fun deleteSession(sessionId: Int)
    fun editSession(sessionData: SessionData, sessionId: Int)
}