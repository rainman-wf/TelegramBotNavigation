package navigation.models

import navigation.frame.Frame
import navigation.log
import java.util.*

data class UserState(
    val userId: UserId,
    private val frameStack: Deque<Frame> = LinkedList(),
    var navSession: Int? = null
) {
    fun addLast(frame: Frame): UserState {
        frameStack.addLast(frame)
        log()
        return this
    }

    val last: Frame get() = frameStack.peekLast()

    val previous: Frame
        get() {
            frameStack.removeLast()
            log()
            return frameStack.peekLast()
        }

    private fun log() {
        log("user: ${userId.value} :: session: $navSession :: $frameStack")
    }

    fun resetStack() : Frame {
        val homeFrame = frameStack.pollFirst()
        frameStack.clear()
        frameStack.addLast(homeFrame)
        log()
        return last
    }
}