package navigation.frame

import navigation.models.UserId

abstract class HomeFrame(private val userId: UserId) : Frame(userId) {

    final override suspend fun show() {
        val sessionId = controller.getNavSession(userId) ?: return
        bot.deleteMessage(userId.value, sessionId)
        controller.setNavSession(userId, null)
    }

}