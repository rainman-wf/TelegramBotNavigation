package botapi

import botapi.models.*
import botapi.sender.RequestPoolExecutor
import botapi.sender.api.*
import okhttp3.logging.HttpLoggingInterceptor

class Bot(token: String) {

    internal val api = InitApi(token)

    enum class AllowedUpdates {
        MESSAGE,
        EDITED_MESSAGE,
        CHANNEL_POST,
        EDITED_CHANNEL_POST,
        INLINE_QUERY,
        CHOSEN_INLINE_RESULT,
        CALLBACK_QUERY,
        SHIPPING_QUERY,
        PRE_CHECKOUT_QUERY,
        POLL,
        POLL_ANSWER,
        MY_CHAT_MEMBER,
        CHAT_MEMBER,
        CHAT_JOIN_REQUEST
    }

    private val allowedUpdates = mutableSetOf<AllowedUpdates>()

    fun setFullUpdates() {
        allowedUpdates.addAll(AllowedUpdates.entries)
    }

    fun excludeUpdates(list: List<AllowedUpdates>) {
        allowedUpdates.addAll(AllowedUpdates.entries.minus(list.toSet()))
    }

    lateinit var pool: RequestPoolExecutor
        private set

    suspend fun updates(body: suspend Update.() -> Unit) {

        val updatesList = if (allowedUpdates.isEmpty()) null else allowedUpdates

        api.poller.poll(updatesList?.map { it.name.lowercase() }).collect {
            body(it)
        }
    }


    suspend fun launchRequestPool() {
        pool = RequestPoolExecutor(this)
        pool.launch()
    }

    enum class LoggingLevel {
        BODY, BASIC, NONE, HEADERS
    }

    fun setLoggingLevel(level: LoggingLevel) {
        api.logging.level = HttpLoggingInterceptor.Level.valueOf(level.name)
    }

}


