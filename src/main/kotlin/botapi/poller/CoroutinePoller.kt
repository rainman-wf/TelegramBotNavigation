package botapi.poller

import botapi.models.Update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

class CoroutinePoller (
    private val api: Api
)  {

    private var lastUpdateId: Int? = null

    fun poll(): Flow<Update> {
        return channelFlow {
            while (true) {
                val data = try {
                    api.getUpdates(lastUpdateId, 90)
                } catch (e: Exception) {
                    println("Update polling error : ${e::class.simpleName} : ${e.message}")
                    delay(5000)
                    continue
                }
                if (!data.isSuccessful) {
                    println(data.errorBody()?.string() ?: "Get Updates error: ${data.code()}")
                    continue
                }
                val body = data.body() ?: PollingUpdatesResult(false, listOf())
                body.result.asFlow().collect {
                    send(it)
                    lastUpdateId = it.updateId + 1
                }
                delay(500)
            }
        }.flowOn(Dispatchers.IO)
    }
}