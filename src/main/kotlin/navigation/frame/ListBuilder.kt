package navigation.frame

import botapi.Bot
import botapi.sender.answerInlineQuery

class ListBuilder<T>(
    private val list: List<T>,
    private val queryId: String,
) {

    lateinit var adapter: ListAdapter<T>

    suspend fun execute(bot: Bot) {
        bot.answerInlineQuery(queryId) {
            results.addAll(adapter.map(list))
            cacheTime = 1
            isPersonal = true
        }
    }
}