package navigation

import botapi.Bot
import botapi.models.Update
import navigation.frame.Frame
import navigation.models.toResponse

fun Navigation.init(bot: Bot, baseFrames: InitRootFrames.() -> Unit) {
    Frame.attachBot(bot)
    val builder = InitRootFrames()
    baseFrames(builder)
    NavigationController.initBaseFrames(builder.frames)
}

class InitRootFrames {

    val frames = mutableMapOf<String, () -> Frame>()

    fun root(command: String, frame: () -> Frame) {
        frames[command] = frame
    }

    fun home(frame: () -> Frame ) {
        frames["/home"] = frame
    }
}

suspend fun Navigation.listen(update: Update) {
    NavigationController.updateHandler(update.toResponse())
}
