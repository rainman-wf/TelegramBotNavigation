package v1nav

import botapi.Bot
import navigation.Navigation
import navigation.frame.*
import navigation.init
import navigation.listen
import navigation.models.NavResponse
import photo1
import photo2
import token


val bot = Bot(token)



suspend fun main() {

    bot.setLoggingLevel(Bot.LoggingLevel.NONE)

    Navigation.init(bot) {
        home(::Home)
        root("/start", ::FirstFrame)
    }

    bot.updates {
        Navigation.listen(this)
    }
}

class Home : HomeFrame()

class FirstFrame : Frame() {
    override suspend fun show() {
        photo {
            photo = photo1
            content {
                "first photo"
            }
            keyboard {
                row {
                    button("Next", "next")
                }
            }
        }
    }
    override suspend fun handle(navResponse: NavResponse) {
        super.handle(navResponse)
        when (navResponse.data) {
            "next" -> navigate(::SecondFrame)

        }
    }
}

class SecondFrame : Frame() {
    override suspend fun show() {
        photo {
            photo = photo2
            content {
                "SECOND PHOTO"
            }
            keyboard {
                row {
                    button("Back", "back")
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





