package navigation

import botapi.models.Update
import navigation.frame.FrameFactory
import navigation.frame.FrameKey
import navigation.models.Request
import navigation.models.toResponse

fun Navigation.init(frameFactory: FrameFactory, keys: List<FrameKey>, botToken: String) {
    NavigationController.attachFactory(frameFactory, keys)
    Request.attachSendingManager(botToken)
}

suspend fun Navigation.listen(update: Update) {
    NavigationController.updateHandler(update.toResponse())
}
