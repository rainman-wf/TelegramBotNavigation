package bot

import bot.models.Update
import bot.poller.Api
import bot.poller.CoroutinePoller
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Bot(private val token: String) {

    private val poller: CoroutinePoller = CoroutinePoller(createApi())

    suspend fun updates(body: suspend  (Update) -> Unit) = poller.poll().collect {
        body(it)
    }

    private fun createApi(): Api {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(75, TimeUnit.SECONDS)
            .readTimeout(75, TimeUnit.SECONDS)
            .writeTimeout(75, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("https://api.telegram.org/bot$token/")
            .build().create(Api::class.java)
    }

}