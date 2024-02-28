package simple

import botapi.Bot
import botapi.models.ReactionTypeEmoji
import botapi.sender.sendMessage
import botapi.sender.setMessageReaction
import myId
import token


suspend fun main() {

    val bot = Bot(token)
    bot.setLoggingLevel(Bot.LoggingLevel.BODY)

    bot.sendMessage(myId, "Hello").result?.let {
        bot.setMessageReaction(it.chat.id, it.messageId) {
            reaction = listOf("👍").map { e ->
                ReactionTypeEmoji(emoji = e)
            }
        }.let {
            if (!it.ok) println(it.errorDescription)
        }
    }

}


