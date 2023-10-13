package simple

import v1nav.bot
import botapi.models.ParseMode
import botapi.sender.sendMessage
import myId
import navigation.models.toMarkdown

suspend fun main() {
    bot.sendMessage(myId, TABLE_PATTERN.trimIndent().toMarkdown()) {
        parseMode = ParseMode.MarkdownV2
    }
}

const val TABLE_PATTERN= """
            ```
               ┌──────────┬──────────┐
               │ column_1 │ column_2 │
               ├──────────┼──────────┤
               │ key_1    │ value_1  │
               │ key_2    │ value_2  │
               └──────────┴──────────┘              
            ```
        """

