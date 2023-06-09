package bot.poller

import bot.models.Update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

class CoroutinePoller (
    private val api: Api
) : Poller {

    private var lastUpdateId: Int? = null

    override fun poll(): Flow<Update> {
        return channelFlow {
            while (true) {
                val data = try {
                    api.getUpdates(lastUpdateId, 30)
                } catch (e: Exception) {
                    println(e.message)
                    delay(1000)
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