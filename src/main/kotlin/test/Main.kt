package test

import botapi.Bot
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
}

class Home : HomeFrame()

class Main : RootFrame() {
    override suspend fun show() {
        text {
            content { "hello" }
            keyboard {
                row {
                    button("Go", "next")
                    button("AiAi" , "aiai")
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
        }
    }
}

class Final : FinalFrame() {

    class Args (
        val name: String
    ): NavArg

    private val args: Args by lazy { navArgs() }

    override suspend fun show() {
        text {
            content { args.name  }
        }
    }
}

