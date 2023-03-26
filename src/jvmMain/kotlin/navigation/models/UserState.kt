package navigation.models

import navigation.frame.Frame
import navigation.log
import java.util.*

data class UserState(
    val userId: UserId,
    private val frameStack: Deque<Frame> = LinkedList(),
    private var _navSession: Int? = null,
) {

    val navSession: Int?  get() = _navSession

    fun setSession(sessionId: Int) {
        _navSession = sessionId
    }

    fun resetSession() {
        _navSession = null
    }

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
        log("user: ${userId.value} :: session: $_navSession :: $frameStack")
    }

    fun resetToRoot(frame: Frame) : Frame {
        val homeFrame = frameStack.pollFirst()
        frameStack.clear()
        frameStack.addLast(homeFrame)
        frameStack.addLast(frame)
        log()
        return last
    }

    fun resetStack() : Frame {
        val homeFrame = frameStack.pollFirst()
        frameStack.clear()
        frameStack.addLast(homeFrame)
        log()
        return last
    }

    fun parent() : Frame {
        val size= frameStack.size
        return frameStack.elementAt(size - 2)
    }

}