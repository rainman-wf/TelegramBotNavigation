package navigation.models

import kotlinx.coroutines.*
import navigation.frame.Frame
import navigation.log
import java.util.*

internal class UserState(private val userId: Long) {

    private val frameStack: Deque<Frame> = LinkedList()
    private var _navSession: Long? = null
    private var autoClose: Job = Job()

    val navSession: Long?  get() = _navSession

    fun setSession(sessionId: Long) {
        _navSession = sessionId
        autoClose.cancel()
        last.autoCloseParams?.let {
            autoClose = CoroutineScope(Dispatchers.Default).launch {
                delay(it.timeout)
                resetStack().let { frame ->
                    if (it.removeCurrent) frame.show()
                    _navSession = null
                }
            }
        }
    }

    fun resetSession() {
        autoClose.cancel()
        _navSession = null
    }

    fun addLast(frame: Frame): UserState {
        frameStack.addLast(frame)
        return this
    }

    fun replaceLast(frame: Frame) : UserState {
        frameStack.removeLast()
        frameStack.addLast(frame)
        return this
    }

    val last: Frame
        get() {
            return frameStack.peekLast()
        }

    val previous: Frame
        get() {
            frameStack.removeLast()
            return frameStack.peekLast()
        }

    private fun log() {
        log("user: $userId :: session: $_navSession :: $frameStack")
    }

    fun resetToRoot(frame: Frame) : Frame {
        val homeFrame = frameStack.pollFirst()
        frameStack.clear()
        frameStack.addLast(homeFrame)
        frameStack.addLast(frame)
        return last
    }

    fun resetStack() : Frame {
        val homeFrame = frameStack.pollFirst()
        frameStack.clear()
        frameStack.addLast(homeFrame)
        return last
    }

    val parent: Frame get() {
        val size= frameStack.size
        return frameStack.elementAt(size - 2)
    }
}