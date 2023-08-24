package botapi

import botapi.models.*
import botapi.poller.Api
import botapi.poller.CoroutinePoller
import botapi.sender.api.InlineModeApi
import botapi.sender.api.SendMsgApi
import botapi.sender.api.UpdateMsgApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Bot(token: String) {

    private val baseUrl = "https://api.telegram.org/bot$token/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    enum class LoggingLevel {
        BODY, BASIC, NONE, HEADERS
    }

    fun setLoggingLevel(level: LoggingLevel) {
        logging.level = HttpLoggingInterceptor.Level.valueOf(level.name)
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(75, TimeUnit.SECONDS)
        .readTimeout(75, TimeUnit.SECONDS)
        .writeTimeout(75, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    private val updatePollerApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build().create(Api::class.java)

    internal val requestSenderApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build().create(SendMsgApi::class.java)

    internal val requestUpdateMsgApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build().create(UpdateMsgApi::class.java)

    internal val inlineModeApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build().create(InlineModeApi::class.java)

    private val poller: CoroutinePoller = CoroutinePoller(updatePollerApi)

    suspend fun updates(body: suspend (Update) -> Unit) = poller.poll().collect {
        body(it)
    }
}


