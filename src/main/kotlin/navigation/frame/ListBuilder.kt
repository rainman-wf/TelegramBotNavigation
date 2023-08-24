package navigation.frame

import botapi.sender.answerInlineQuery

class ListBuilder<T>(
    private val list: List<T>,
    private val queryId: String,
) {

    lateinit var adapter: ListAdapter<T>

    suspend fun execute() {
        Frame.bot.answerInlineQuery(queryId) {
            results.addAll(adapter.map(list))
            cacheTime = 10
            isPersonal = true
        }
    }
}