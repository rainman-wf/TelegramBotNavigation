package navigation.models

import kotlinx.coroutines.*
import navigation.frame.AutoCloseable
import navigation.frame.Frame
import navigation.frame.HomeFrame
import navigation.log
import java.util.*

internal class UserState(
    private val userId: Long,
    private val homeFrame: HomeFrame
) {

    private val frameStack: Deque<Frame> = LinkedList()
    private var _navSession: Long? = null
    private var autoClose: Job = Job()

    val navSession: Long?  get() = _navSession

    fun setSession(sessionId: Long) {
        _navSession = sessionId
        autoClose.cancel()
        last.let {
            if (it is AutoCloseable) {
                autoClose = CoroutineScope(Dispatchers.Default).launch {
                    delay(it.timeout * 1000L)
                    home().let { frame ->
                        if (it.removeCurrent) frame.show()
                        _navSession = null
                    }
                }
            }
        }
        log()
    }

    fun resetSession() {
        autoClose.cancel()
        _navSession = null
        log()
    }

    fun resetStack() : UserState {
        frameStack.clear()
        return this
    }

    fun addLast(frame: Frame): UserState {
        frameStack.addLast(frame)
        return this
    }

    fun replaceLast(frame: Frame) : UserState {
        removeLast()
        frameStack.addLast(frame)
        return this
    }

    val last: Frame
        get() {
            return frameStack.peekLast() ?: homeFrame
        }

    val previous: Frame
        get() {
            removeLast()
            return last
        }

    private fun log() {
        log("user: $userId :: session: $_navSession :: $frameStack")
    }

    fun resetToRoot(frame: Frame) : Frame {
        frameStack.clear()
        frameStack.addLast(frame)
        return last
    }

    fun home() : Frame {
        frameStack.clear()
        return homeFrame
    }

    val parent: Frame get() {
        val size = frameStack.size
        return frameStack.elementAt(size - 2)
    }

    override fun toString(): String {
        return this::class.simpleName.toString() + " : id $userId"
    }

    private fun removeLast() {
        if (frameStack.isNotEmpty()) frameStack.removeLast()
    }
}