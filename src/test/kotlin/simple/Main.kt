package simple

import botapi.Bot
import botapi.models.ParseMode
import botapi.sender.sendMessage
import myId
import token
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

suspend fun main() {

    val bot = Bot(token)

    val regex = "/start [A-Za-zА-Яа-яЁё0-9_]{4,256}$".toRegex()

    bot.updates {
        message?.text?.let {
            if (it.matches(regex)) {
                bot.sendMessage(myId, "utm = ${it.substringAfter("/start ")}")
            }
        }
    }

}


