package botapi.sender.api

import botapi.poller.Api
import botapi.poller.CoroutinePoller
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class InitApi(token: String) {

    private val baseUrl = "https://api.telegram.org/bot$token/"

    internal val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val converterFactory = GsonConverterFactory.create()

    private val client = OkHttpClient.Builder()
        .connectTimeout(75, TimeUnit.SECONDS)
        .readTimeout(75, TimeUnit.SECONDS)
        .writeTimeout(75, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    internal val updatePollerApi = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .client(client)
        .baseUrl(baseUrl)
        .build().create(Api::class.java)

    internal val requestSenderApi = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .client(client)
        .baseUrl(baseUrl)
        .build().create(SendMsgApi::class.java)

    internal val requestUpdateMsgApi = Retrofit.Builder()
        .addConverterFactory(converterFactory
        )
        .client(client)
        .baseUrl(baseUrl)
        .build().create(UpdateMsgApi::class.java)

    internal val inlineModeApi = Retrofit.Builder()
        .addConverterFactory(converterFactory
        )
        .client(client)
        .baseUrl(baseUrl)
        .build().create(InlineModeApi::class.java)

    internal val getter = Retrofit.Builder()
        .addConverterFactory(converterFactory
        )
        .client(client)
        .baseUrl(baseUrl)
        .build().create(GetDataApi::class.java)

    internal val groupsActions = Retrofit.Builder()
        .addConverterFactory(converterFactory
        )
        .client(client)
        .baseUrl(baseUrl)
        .build().create(GroupsActionsApi::class.java)

    internal val poller: CoroutinePoller = CoroutinePoller(updatePollerApi)
}