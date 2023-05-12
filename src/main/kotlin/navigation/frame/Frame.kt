package navigation.frame

import botapi.Bot
import botapi.models.*
import botapi.sender.RequestBuilder
import navigation.NavigationController
import navigation.args.ArgName
import navigation.args.ArgsContainer
import navigation.args.NavArg
import navigation.log
import navigation.models.*
import java.io.File

abstract class Frame(private val userId: UserId, private val args: ArgsContainer? = null) {

    internal val controller = NavigationController

    private var result: NavArg? = null

    internal var chainMode: Boolean = false

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

    suspend fun text(block: Message.() -> Unit) {
        val msgId = controller.getNavSession(userId)
        val builder = Message(msgId)
        block(builder)
        val response = builder.execute()
        setNavSession(response.result?.messageId)
    }

    suspend fun photo(block: Photo.() -> Unit) {
        val msgId = controller.getNavSession(userId)
        val builder = Photo(msgId)
        block(builder)
        val response = builder.execute()
        setNavSession(response.result?.messageId)
    }

    suspend fun file(block: Document.() -> Unit) {
        val msgId = controller.getNavSession(userId)
        val builder = Document(msgId)
        block(builder)
        val response = builder.execute()
        log(response)
        setNavSession(response.result?.messageId)
    }

    internal fun setNavSession(messageId: Long?) {
        val msgId = if (chainMode) null else messageId
        controller.setNavSession(userId, msgId)
    }

    suspend fun repeat() {
        controller.repeat(userId)
    }

    inner class Message(private val messageId: Long? = null) : RequestBuilder {
        lateinit var text: String
        var keyboard: ReplyMarkup? = null
        var formatted: Boolean = false

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

    inner class Photo(private val messageId: Long? = null) : RequestBuilder {
        lateinit var photo: Any
        var keyboard: ReplyMarkup? = null
        var formatted: Boolean = false
        var text: String? = null

        suspend fun execute() = when (photo) {
            is File -> bot.sendPhoto(
                userId.value,
                photo as File
            ) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                caption = text
            }

            is String -> bot.sendPhoto(
                userId.value,
                photo as String
            ) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                caption = text
            }

            else -> throw IllegalArgumentException("photo must be File or string (id / url)")
        }
    }

    inner class Document(private val messageId: Long? = null) : RequestBuilder {
        lateinit var document: Any
        var keyboard: ReplyMarkup? = null
        var formatted: Boolean = false
        var text: String? = null

        suspend fun execute() = when (document) {
            is File -> bot.sendDocument(
                userId.value,
                document as File
            ) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                caption = text
            }

            is String -> bot.sendDocument(
                userId.value,
                document as String
            ) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                caption = text
            }

            else -> throw IllegalArgumentException("document must be File or string (id / url)")
        }
    }

    suspend fun chain(body: suspend () -> Unit) {
        chainMode = true
        body()
        chainMode = false
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

    suspend fun navigateWithReset (key: FrameKey, vararg args: Pair<ArgName, NavArg>) {
        setNavSession(null)
        controller.navigate(userId, key, args)
    }

    suspend fun back() = controller.back(userId)

    override fun toString(): String = this::class.simpleName.toString()
}




