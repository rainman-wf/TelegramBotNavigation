package navigation.models

import com.pengrad.telegrambot.TelegramBot
import navigation.sender.MessageSendingManager

sealed interface Request {

    companion object {
        private lateinit var sendMessageManager: MessageSendingManager
        fun attachSendingManager(botToken: String) {
            sendMessageManager = MessageSendingManager(TelegramBot(botToken))
        }
    }

    val toUserId: UserId

    suspend fun execute(): Response {
        val request = when (this) {
            is NewMessage -> this.toRequest()
            is DeleteMessage -> this.toRequest()
            is DataListRequest<*> -> error("use DataListRequest interface")
            is PopUpMsg -> this.toRequest()
        }
        return send(request)
    }

    suspend fun send(request: RequestType<*>) = sendMessageManager.prioritySending(request).toResponse()
}

