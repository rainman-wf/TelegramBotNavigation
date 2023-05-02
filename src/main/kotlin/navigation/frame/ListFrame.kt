package navigation.frame

import botapi.models.InlineQueryResult
import botapi.models.InlineQueryResultArticle
import navigation.args.ArgsContainer
import navigation.models.UserId

abstract class ListFrame(private val userId: UserId, args: ArgsContainer? = null) : Frame(userId, args) {

    suspend fun <T> list(list: List<T>, queryId: String, block: DataList<T>.() -> Unit) {
        val builder = DataList(list, queryId)
        block(builder)
        builder.execute()
    }

    inner class DataList<T>(
        private val list: List<T>,
        private val queryId: String,
    ) {
        lateinit var adapter: DataListAdapter<T>

        suspend fun execute() {
            bot.answerInlineQuery(queryId) {
                results.addAll(adapter.map(list))
                cacheTime = 10
                isPersonal = true
            }
        }

    }

    interface DataListAdapter<T> {
        fun map(data: List<T>): List<InlineQueryResult>
    }
}