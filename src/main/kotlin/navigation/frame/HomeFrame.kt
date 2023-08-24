package navigation.frame

import botapi.sender.deleteMessage


abstract class HomeFrame : Frame() {

    final override suspend fun show() {
        val sessionId = controller.getNavSession(userId) ?: return
        bot.deleteMessage(userId, sessionId)
        controller.setNavSession(userId, null)
    }
}

abstract class RootFrame : Frame()

abstract class FinalFrame : Frame()