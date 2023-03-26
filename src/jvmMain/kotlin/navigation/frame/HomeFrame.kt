package navigation.frame

import navigation.log
import navigation.models.DeleteMessage
import navigation.models.UserId

abstract class HomeFrame(private val userId: UserId) : Frame(userId) {

    final override suspend fun show() {
        val sessionId = controller.getNavSession(userId) ?: return
        DeleteMessage(userId, sessionId).execute()
        controller.setNavSession(userId, null)
    }

}