package botapi

import botapi.models.*
import botapi.sender.RequestPoolExecutor
import botapi.sender.api.*
import okhttp3.logging.HttpLoggingInterceptor

class Bot(token: String) {

    internal val api = InitApi(token)

    lateinit var pool: RequestPoolExecutor
        private set

    suspend fun updates(body: suspend Update.() -> Unit) = api.poller.poll().collect {
        body(it)
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


