package navv2.presenters

import botapi.Bot
import botapi.models.InputMediaPhoto
import botapi.models.ParseMode
import botapi.sender.editMessageMedia
import botapi.sender.sendPhoto
import navigation.NavComponent
import navigation.models.toMarkdown
import java.io.File

class PhotoMsgBuilder(private val bot: Bot, private val userId: Long, private val messageId: Long? = null) : NavComponent() {
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
                protectContent = _protected
            }

            is String -> bot.sendPhoto(
                userId,
                photo as String
            ) {
                if (formatted) parseMode = ParseMode.MarkdownV2
                replyMarkup = keyboard
                caption = content?.let { if (formatted) it.toMarkdown() else it }
                protectContent = _protected
            }

            else -> throw IllegalArgumentException("photo must be File or string (id / url)")
        }
    }