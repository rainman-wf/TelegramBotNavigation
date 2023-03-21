package navigation.sender

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.response.BaseResponse
import kotlinx.coroutines.delay
import navigation.log
import navigation.models.*

class MessageSendingManager (
    private val bot: TelegramBot,
) {

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
            if (result.isOk || result.errorCode() == 403 || result.errorCode() == 400) {
                result.description()?.let{ d -> log("${result.errorCode()} :: $d")}
                break
            }
            if (result.parameters()?.retryAfter() != null) {
                log("${result.errorCode()} :: ${result.description()}")
                delay(result.parameters().retryAfter().toLong())
            }
            if (!result.isOk) {
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