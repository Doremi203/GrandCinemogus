package bll.controllers.interfaces

import pll.models.input.SessionData
import pll.models.output.SessionOutput
import java.util.UUID

interface SessionsController {
    fun addSession(newSessionData: SessionData)
}