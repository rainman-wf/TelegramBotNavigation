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
        root("/settings", ::Settings)
    }

    bot.updates {
        Navigation.listen(it)
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

class Settings : RootFrame() {

    class WaitingResult (
        val value: Int
    ) : NavArg

    override suspend fun show() {
        text {
            content {
                "Settings: result ${results<WaitingResult>()?.value ?: "NULL"}"
            }

            keyboard {
                row {
                    button("Go", "yes")

                }
            }
        }
    }

    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
        when (navResponse.data) {
            "yes" -> navigateForResult(::ResultEditor)
        }
    }
}

class ResultEditor: Frame() {
    override suspend fun show() {
        text {
            content {
                "input result"
            }

            keyboard {
                row {
                    button("YaYa", "1")
                    button("NaNa", "2")
                }
            }
        }
    }

    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
        when (navResponse.data) {
            "1", "2" -> back(Settings.WaitingResult(navResponse.data.toInt()))
            else -> back()
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




