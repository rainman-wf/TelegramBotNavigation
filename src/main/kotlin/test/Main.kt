package test

import botapi.Bot
import botapi.sender.forwardMessage
import navigation.Navigation
import navigation.args.NavArg
import navigation.frame.*
import navigation.init
import navigation.listen
import navigation.models.NavResponse


val bot = Bot("5168485531:AAE0sxjvdc6O1voIxvRwqs9klag5VSqIVbs")

suspend fun main() {

    bot.setLoggingLevel(Bot.LoggingLevel.NONE)
    Navigation.init(bot) {
        home(::Home)
        root("/start", ::Main)
    }

    bot.updates {
        Navigation.listen(it)
    }

    bot.forwardMessage(1, 2, 3) {
        protectContent = true
        disableNotification = true
    }
}

class Home : HomeFrame() {
    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
//        when (navResponse.data) {
//            "/start" -> navigate(::Main)
//        }
    }
}

class Main : RootFrame() {

    class ResultArgs(
        val value: String
    ) : NavArg

    override suspend fun show() {
        text {
            content {
                getResult<ResultArgs>()?.value ?: "Hello"
            }
            keyboard {
                row {
                    button("Go", "next")
                    button("AiAi", "aiai")
                }
            }
        }
    }

    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
        when (navResponse.data) {
            "next" -> navigate(::Final, Final.Args(navResponse.firstName))
            "aiai" -> navigate(::SomeFrame)
        }
    }
}


class SomeFrame : Frame() {
    override suspend fun show() {
        text {
            content { "Aiaiai" }
            keyboard {
                row {
                    button("back", "back")
                }
            }
        }
    }

    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
        when (navResponse.data) {
            "back" -> back()
            else -> back(Main.ResultArgs(navResponse.data))
        }
    }
}

class Final : FinalFrame() {

    class Args(
        val name: String
    ) : NavArg

    private val args: Args by lazy { navArgs() }

    override suspend fun show() {
        text {
            content { args.name }
        }
    }
}



