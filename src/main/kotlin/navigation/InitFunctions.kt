package navigation

import botapi.Bot
import botapi.models.Update
import navigation.frame.Frame
import navigation.frame.FrameFactory
import navigation.frame.FrameKey
import navigation.models.toResponse

fun Navigation.init(frameFactory: FrameFactory, keys: List<FrameKey>, bot: Bot) {
    NavigationController.attachFactory(frameFactory, keys)
    Frame.attachBot(bot)
}

suspend fun Navigation.listen(update: Update) {
    NavigationController.updateHandler(update.toResponse())
}
