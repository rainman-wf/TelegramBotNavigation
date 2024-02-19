package navigation

import botapi.Bot
import botapi.models.Update
import navigation.models.toResponse

interface Context {

    val context: Context get() = _instance
    fun attachContext(context: Context) = setContext(context)

    val bot: Bot get() = _bot
    fun init(bot: Bot, baseFrames: InitRootFrames.() -> Unit) = _init(bot, baseFrames)

    suspend fun listen(update: Update) {
        update.apply {
            (message ?: editedMessage ?: callbackQuery ?: inlineQuery ?: chosenInlineResult ?: channelPost)
                ?.let { NavigationController.updateHandler(toResponse()) }
                ?: println("Context unhandled update ${update::class.simpleName}")
        }
    }

    companion object {

        private lateinit var _instance: Context

        private fun setContext(app: Context) {
            _instance = app
        }

        private lateinit var _bot: Bot
        private fun setBot(bot: Bot) {
            _bot = bot
        }

        private fun _init(bot: Bot, baseFrames: InitRootFrames.() -> Unit) {
            setBot(bot)
            val builder = InitRootFrames()
            baseFrames(builder)
            NavigationController.initBaseFrames(builder.frames)
        }


    }
}
