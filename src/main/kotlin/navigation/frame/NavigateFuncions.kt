package navigation.frame

import botapi.sender.answerCallbackQuery
import navigation.args.NavArg

suspend fun Frame.navigate(constructor: () -> Frame, args: NavArg? = null) {

    controller.navigate(userId, constructor, args)
}

suspend fun Frame.navigateForResult(constructor: () -> Frame, args: NavArg? = null) {
    resetResult()
    controller.navigate(userId, constructor, args, this)
}

suspend fun Frame.back(args: NavArg) {
    args.apply {
        parent?.putResult(this)
            ?: throw IllegalArgumentException("Use navigateWithResult() to this frame for back with result")
    }
    controller.back(userId)
}

suspend fun Frame.back() {
    controller.back(userId)
}

suspend fun Frame.back(steps: Int) {
    controller.back(userId, steps)
}

suspend fun Frame.home() = controller.home(userId)

suspend fun Frame.update() = controller.update(userId)

suspend fun Frame.replace(constructor: () -> Frame, args: NavArg? = null) =
    controller.replace(userId, constructor, args)

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