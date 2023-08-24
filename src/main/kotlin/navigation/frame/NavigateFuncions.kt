package navigation.frame

import botapi.sender.answerCallbackQuery
import navigation.args.NavArg

suspend fun Frame.navigate(constructor: () -> Frame, args: NavArg? = null) = controller.navigate(userId, constructor, args)
suspend fun Frame.back(args: NavArg? = null) {
    args?.apply {
        controller.parentFrame(userId).putResult(this)
    }
    controller.back(userId)
}

suspend fun Frame.home() = controller.home(userId)

suspend fun Frame.update() = controller.update(userId)

suspend fun Frame.replace(constructor: () -> Frame, args: NavArg? = null) = controller.replace(userId, constructor, args)

suspend fun Frame.popUp(callbackId: String?, text: String, okButton: Boolean = true) {
    callbackId?.let {
        Frame.bot.answerCallbackQuery(it) {
            this.text = text
            this.showAlert = okButton
        }
    }
}

suspend fun Frame.repeat() = controller.repeat(userId)

suspend fun Frame.next(constructor: () -> Frame, args: NavArg? = null) {
    setNavSession(null)
    return controller.navigate(userId, constructor, args)
}