package navigation.sender

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.response.BaseResponse
import kotlinx.coroutines.delay
import navigation.log
import navigation.models.*

object MessageSendingManager {

    private lateinit var bot: TelegramBot

    fun attachBot(telegramBot: TelegramBot) {
        bot = telegramBot
    }

    private val executor = QueueTasksExecutor<RequestType<*>, BaseResponse>(150) {
        var result: BaseResponse
        do {
            result = when (it) {
                is SendMessageDto -> bot.execute(it.request)
                is ForwardMessageDto -> bot.execute(it.request)
                is EditMessageDto -> bot.execute(it.request)
                is DeleteMessageDto -> bot.execute(it.request)
                is AnswerCallbackQueryDto -> bot.execute(it.request)
                is AnswerInlineQueryDto -> bot.execute(it.request)
            }
            if (result.isOk) break

            if (result.parameters()?.retryAfter() != null) {
                log("${result.errorCode()} :: ${result.description()}")
                delay(result.parameters().retryAfter().toLong() * 1000)
            }

            if (!result.isOk) {
                when (result.errorCode()) {
                    400, 403 -> break
                }
                log("${result.errorCode()} :: ${result.description()}")
            }
        } while (true)
        result
    }

    suspend fun mailing(data: RequestType<*>): BaseResponse {
        return executor.emit(data)
    }

    suspend fun prioritySending(data: RequestType<*>): BaseResponse {
        return executor.emitPriority(data)
    }
}