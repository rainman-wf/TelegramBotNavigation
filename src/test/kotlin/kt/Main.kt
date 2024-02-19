package kt

import botapi.Bot
import navigation.Context
import navigation.frame.Frame
import navigation.frame.HomeFrame
import navigation.frame.text
import token


class BotApp : Context, UiProvider {

    override val value: String = "Double hello"

}

suspend fun main() {
    val app = BotApp()
    val bot = Bot(token)
    app.init(bot) {
        home { Home() }
        root("/start") { Main() }
    }
    app.attachContext(app)
    bot.updates {
        app.listen(this)
    }
}


interface UiProvider {

    val value: String

}

class Home : HomeFrame()

class Main : Frame() {
    override suspend fun show() {
        text { content { (context as UiProvider).value } }
    }
}