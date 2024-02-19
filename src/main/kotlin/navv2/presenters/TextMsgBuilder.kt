package navv2.presenters

import botapi.Bot
import botapi.models.ParseMode
import botapi.sender.editMessageText
import botapi.sender.sendMessage
import navigation.NavComponent
import navigation.models.toMarkdown

class TextMsgBuilder(private val bot: Bot, private val userId: Long, private val messageId: Long? = null) : NavComponent() {
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