package navigation.frame

import botapi.models.*
import botapi.sender.*
import navigation.NavComponent
import navigation.NavigationController
import navigation.args.NavArg
import navigation.Context
import navigation.models.*
import java.io.File
import kotlin.properties.Delegates

abstract class Frame : Context {

    internal val controller = NavigationController

    private var _parent: Frame? = null
    val parent get() = _parent
    internal fun setParentFrame(frame: Frame): Frame {
        _parent = frame
        return this
    }

    private lateinit var _user: User
    val user get() = _user

    val userId get() = user.id
    internal fun setUser(user: User): Frame {
        _user = user
        return this
    }

    private var _args: NavArg? = null

    @Suppress("UNCHECKED_CAST")
    fun <T : NavArg?> navArgs() = _args as T?
    internal fun setArgs(args: NavArg): Frame {
        _args = args
        return this
    }

    private var _result: NavArg? = null

    @Suppress("UNCHECKED_CAST")
    fun <T : NavArg?> results() = _result as T?
    internal fun putResult(arg: NavArg) {
        _result = arg
    }

    internal fun resetResult() {
        _result = null
    }

    private var chainMode: Boolean = false


    fun setChainMode() {
        chainMode = true
    }

    fun resetChainMode() {
        chainMode = false
    }

    abstract suspend fun show()

    open suspend fun handle(navResponse: NavResponse) {
        navResponse.messageId?.let { bot.deleteMessage(userId, it) }
        navResponse.callbackId?.let { bot.answerCallbackQuery(it) { cacheTime = 1 } }
    }

    internal fun setNavSession(messageId: Long?) {
        val msgId = if (chainMode) null else messageId
        controller.setNavSession(userId, msgId)
    }


    inner class Message(private val messageId: Long? = null) : NavComponent() {

        suspend fun execute() = if (messageId == null) {
            bot.sendMessage(
                chatId = userId,
                text = content?.let { if (formatted) it.toMarkdown() else it }
                    ?: throw IllegalArgumentException("Message content is must not be null")) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                protectContent = protected
            }
        } else {
            bot.editMessageText(
                chatId = userId,
                messageId = messageId,
                text = content?.let { if (formatted) it.toMarkdown() else it }
                    ?: throw IllegalArgumentException("Message content is must not be null")) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
            }
        }
    }

    inner class Photo(private val messageId: Long? = null) : NavComponent() {
        lateinit var photo: Any

        suspend fun execute() = messageId?.let { id ->
            if (photo is String) {
                bot.editMessageMedia(userId, id) {
                    replyMarkup = keyboard
                    buildMedia<InputMediaPhoto> {
                        this.caption = content?.let { if (formatted) it.toMarkdown() else it }
                        media = photo as String
                    }
                }
            } else throw IllegalArgumentException("photo must be a string (id / url)")
        } ?: when (photo) {
            is File -> bot.sendPhoto(
                userId,
                photo as File
            ) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                caption = content?.let { if (formatted) it.toMarkdown() else it }
                protectContent = protected
            }

            is String -> bot.sendPhoto(
                userId,
                photo as String
            ) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                caption = content?.let { if (formatted) it.toMarkdown() else it }
                protectContent = protected
            }

            else -> throw IllegalArgumentException("photo must be File or string (id / url)")
        }
    }

    inner class Document(private val messageId: Long? = null) : NavComponent() {
        lateinit var document: Any


        suspend fun execute() = when (document) {
            is File -> bot.sendDocument(
                userId,
                document as File
            ) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                caption = content?.let { if (formatted) it.toMarkdown() else it }
                protectContent = protected
            }

            is String -> bot.sendDocument(
                userId,
                document as String
            ) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                caption = content?.let { if (formatted) it.toMarkdown() else it }
                protectContent = protected
            }

            else -> throw IllegalArgumentException("document must be File or string (id / url)")
        }
    }

    override fun toString(): String = this::class.simpleName.toString()
}




