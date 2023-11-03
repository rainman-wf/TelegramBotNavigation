package navv2.presenters

import botapi.Bot
import botapi.models.ParseMode
import botapi.sender.sendDocument
import navigation.NavComponent
import navigation.models.toMarkdown

import java.io.File

class DocumentMsgBuilder(private val bot: Bot, private val userId: Long, private val messageId: Long? = null) : NavComponent() {

    private lateinit var document: Any

    suspend fun execute() = when (document) {
        is File -> bot.sendDocument(
            userId,
            document as File
        ) {
            if (formatted) parseMode = ParseMode.MarkdownV2
            replyMarkup = keyboard
            caption = content?.let { if (formatted) it.toMarkdown() else it }
            protectContent = _protected
        }

        is String -> bot.sendDocument(
            userId,
            document as String
        ) {
            if (formatted) parseMode = ParseMode.MarkdownV2
            replyMarkup = keyboard
            caption = content?.let { if (formatted) it.toMarkdown() else it }
            protectContent = _protected
        }

        else -> throw IllegalArgumentException("document must be File or string (id / url)")
    }
}
