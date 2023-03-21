package navigation

import bot.models.Update
import navigation.frame.FrameFactory
import navigation.frame.FrameKey
import navigation.models.Request
import navigation.models.toResponse

fun Navigation.init(frameFactory: FrameFactory, homeKey: FrameKey, botToken: String) {
    NavigationController.attachFactory(frameFactory, homeKey)
    Request.attachSendingManager(botToken)
}

suspend fun Navigation.listen(update: Update) {
    NavigationController.updateHandler(update.toResponse())
}