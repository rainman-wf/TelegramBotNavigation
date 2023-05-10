package navigation.frame

import botapi.Bot
import botapi.models.*
import botapi.sender.RequestBuilder
import navigation.NavigationController
import navigation.args.ArgName
import navigation.args.ArgsContainer
import navigation.args.NavArg
import navigation.models.*

abstract class Frame(private val userId: UserId, private val args: ArgsContainer? = null) {

    internal val controller = NavigationController

    private var result: NavArg? = null

    internal companion object {
        lateinit var bot: Bot

        fun attachBot(bot: Bot) {
            this.bot = bot
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : NavArg?> getResult(): T? {
        return result as T?
    }

    private suspend fun putResult(arg: NavArg) {
        result = arg
    }

    private suspend fun parentFrame(): Frame = controller.parentFrame(userId)

    @Suppress("UNCHECKED_CAST")
    fun <T : NavArg> arg(name: ArgName) = args?.args?.get(name) as T

    abstract suspend fun show()

    suspend fun text(block: NewMessage.() -> Unit) {
        val msgId = controller.getNavSession(userId)
        val builder = NewMessage(userId, msgId)
        block(builder)
        val response = builder.execute()
        controller.setNavSession(userId, response.result?.messageId)
    }

    suspend fun chain(vararg block: NewMessage.() -> Unit) {
        block.forEach {
            val builder = NewMessage(userId, null)
            it(builder)
            builder.execute()
        }
    }

    suspend fun repeat() {
        controller.repeat(userId)
    }

    inner class NewMessage(val toUserId: UserId, private val messageId: Long? = null) : RequestBuilder {
        lateinit var text: String
        var keyboard: ReplyMarkup? = null
        var formatted: Boolean = false

        fun <T> grid(list: List<T>, columns: Int, adapter: GridAdapter<T>): InlineKeyboardMarkup {
            val buttons = adapter.map(list)
            val grouped = buttons.groupBy(columns).map { it.toList() }
            return InlineKeyboardMarkup(grouped)
        }

        private fun <T> Collection<T>.groupBy(quantity: Int): Collection<Collection<T>> {
            return withIndex()
                .groupBy { it.index / quantity }
                .map { it.value.map { index -> index.value } }
        }

        suspend fun execute() = if (messageId == null) {
            bot.sendMessage(chatId = userId.value, text = text) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
            }
        } else {
            bot.editMessageText(chatId = userId.value, messageId = messageId, text = text) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
            }
        }
    }




    interface GridAdapter<T> {
        fun map(data: List<T>): List<InlineKeyboardButton>
    }

    suspend fun home() = controller.home(userId)

    suspend fun popUp(callbackId: String, text: String) {
        bot.answerCallbackQuery(callbackId, text) {
            this.showAlert = true
        }
    }

    open suspend fun handle(navResponse: NavResponse) {
        navResponse.messageId?.let { deleteInput(it) }
    }

    private suspend fun deleteInput(messageId: Long) {
        bot.deleteMessage(userId.value, messageId)
    }

    suspend fun <T : NavArg> navigateForResult(
        key: FrameKey,
        vararg args: Pair<ArgName, NavArg>,
    ) {
        controller.navigate(userId, key, args)
    }

    suspend fun update() {
        controller.update(userId)
    }

    suspend fun backWithResult(arg: NavArg) {
        parentFrame().putResult(arg)
        controller.back(userId)
    }

    suspend fun navigate(key: FrameKey, vararg args: Pair<ArgName, NavArg>) = controller.navigate(userId, key, args)
    suspend fun back() = controller.back(userId)

    override fun toString(): String = this::class.simpleName.toString()
}




