package pll

import kotlinx.datetime.LocalDateTime

interface InputReader {
    fun readIntUntilNotNull(): Int
    fun readStringUntilNotNull(): String
    fun readDateTimeUntilNotNull(): LocalDateTime
    fun readDoubleUntilNotNull(): Double
}