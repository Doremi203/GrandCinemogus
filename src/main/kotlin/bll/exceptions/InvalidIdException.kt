package bll.exceptions

class InvalidIdException : ControllerException {
    val id: Int?

    constructor() : super() {
        this.id = null
    }

    constructor(message: String?, id: Int?) : super(message) {
        this.id = id
    }

    constructor(message: String?, cause: Throwable?, id: Int?) : super(message, cause) {
        this.id = id
    }

    constructor(cause: Throwable?, id: Int?) : super(cause) {
        this.id = id
    }

    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean, id: Int?) : super(
        message,
        cause,
        enableSuppression,
        writableStackTrace
    ) {
        this.id = id
    }
}