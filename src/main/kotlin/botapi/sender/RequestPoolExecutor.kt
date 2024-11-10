package botapi.sender

import botapi.Bot
import botapi.models.BaseResponse
import botapi.models.Message
import botapi.models.SendResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class RequestPoolExecutor (
    private val bot: Bot
){

    private val sender = MutableSharedFlow<SendingPackage<SendResponse>>()

    internal suspend fun launch() {

        CoroutineScope(Dispatchers.Default).launch {
            sender.collect {
                var counter = 0
                var errorCounter = 0
                val time = Date().time
                val forbiddenList = mutableListOf<Long>()

                it.pack.forEach { body ->

                    var flag = true

                    while (flag) {
                        val result = body.second.invoke()

                        if (result.ok) {
                            counter++
                            flag = false
                            result.result?.apply {
                                if (this is Message && it.pinMessage == true) {
                                    delay(150)
                                    bot.pinChatMessage(chat.id, messageId)
                                }
                            }
                        } else {

                            println(
                                String.format(
                                    "${
                                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss"))
                                    } | %7s | %-14d | %s",
                                    "Error",
                                    body.first,
                                    result.errorDescription ?: "stub",
                                )
                            )

                            result.parameters?.retryAfter?.let {delay ->
                                delay(delay * 1000L)
                            } ?: run {
                                errorCounter++
                                flag = false
                                if (result.errorCode == 403) forbiddenList.add(body.first)
                            }
                        }
                    }

                    delay(150)
                }

                it.result (SendingResult(counter, errorCounter, Date().time - time, forbiddenList))
            }
        }
    }

    @Suppress("UNCHECKED_CAST", "UNUSED")
    suspend fun <T : SendResponse> send(pack: SendingPackage<T>) {
        sender.emit(pack as SendingPackage<SendResponse>)
    }

    data class SendingPackage<T : SendResponse> (
        val pack: List<Pair<Long, suspend () -> BaseResponse<T>>>,
        val pinMessage: Boolean? = false,
        val result: suspend (SendingResult) -> Unit = {}
    )

    data class SendingResult (
        val successCount: Int,
        val errorCount: Int,
        val time: Long,
        val forbiddenUserList: List<Long>
    )
}