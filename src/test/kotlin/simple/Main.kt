package simple

import botapi.Bot
import botapi.models.ParseMode
import botapi.models.ReactionTypeEmoji
import botapi.sender.sendMessage
import botapi.sender.setMessageReaction
import myId
import token


suspend fun main() {

    val bot = Bot(token)
    bot.setLoggingLevel(Bot.LoggingLevel.BODY)

    bot.sendMessage(myId,
        "[Я Ната — психолог, сексолог и твой проводник к женскому счастью](https://www.instagram.com/nataytali?igsh=eG1xamsxNHdkdWRx)"
    ) {
        parseMode = ParseMode.MarkdownV2
    }

}


