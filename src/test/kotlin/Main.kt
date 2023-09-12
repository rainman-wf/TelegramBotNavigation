import botapi.Bot
import navigation.Navigation
import navigation.args.NavArg
import navigation.frame.*
import navigation.init
import navigation.listen
import navigation.models.NavResponse


val bot = Bot("TOKEN")

suspend fun main() {

    bot.setLoggingLevel(Bot.LoggingLevel.NONE)
    Navigation.init(bot) {
        home(::Home)
        root("/start", ::Main)
    }

    bot.updates {
        Navigation.listen(this)
    }
}

class Home : HomeFrame()

class Main : RootFrame() {
    override suspend fun show() {
        text {
            content {
                "Main Frame"
            }

            keyboard {
                row {
                    button("Next", "next")
                    button("Back", "back")
                }
            }
        }
    }

    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
        when(navResponse.data) {
            "next" -> navigate(::SomeMenuPoint)
            "back" -> back()
        }
    }
}

class SomeMenuPoint : Frame(), AutoCloseable {

    override val timeout = 5
    override val removeCurrent = true
    override suspend fun show() {
        text {
            content {
                "Wait fo close $timeout"
            }
        }
    }
}




