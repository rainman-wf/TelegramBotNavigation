package navigation

import navigation.args.NavArg
import navigation.frame.*
import navigation.models.NavResponse
import navigation.models.UserState

internal object NavigationController {

    private val states = mutableMapOf<Long, UserState>()
    private val roots = mutableMapOf<String, () -> Frame>()

    private inline fun <reified T : Frame> createFrame(userId: Long, args: NavArg? = null, constructor: () -> T): T {
        val builder = constructor().setUserId(userId)
        args?.let { builder.setArgs(it) }
        return builder as T
    }

    fun initBaseFrames(frames: Map<String, () -> Frame>) {
        roots.putAll(frames)
    }

    suspend fun updateHandler(navResponse: NavResponse) {
        if (!createState(navResponse)) return
        if (navResponse.data == "/home") home(navResponse.userId)
        roots[navResponse.data]?.let {
            val frame = createFrame(navResponse.userId) { it.invoke() }
            states[navResponse.userId]!!.resetToRoot(frame).show()
        }
        states[navResponse.userId]?.last?.handle(navResponse)
    }

    suspend fun navigate(userId: Long, constructor: () -> Frame, args: NavArg? = null) {
        val frame = createFrame(userId, args) { constructor() }
        frame.show()

        val state = states[userId]!!

        if (frame is FinalFrame) {
            state.resetSession()
            state.resetStack()
        } else {
            state.addLast(frame)
        }
    }

    suspend fun replace(userId: Long, constructor: () -> Frame, args: NavArg? = null) {
        val frame = createFrame(userId, args) { constructor() }
        frame.show()

        val state = states[userId]!!

        if (frame is FinalFrame) {
            state.resetSession()
            state.resetStack()
        } else {
            state.replaceLast(frame)
        }
    }

    suspend fun update(userId: Long) {
        states[userId]!!.last.show()
    }

    fun parentFrame(userId: Long): Frame {
        return states[userId]!!.parent
    }

    suspend fun back(userId: Long) {
        val currentFrame = states[userId]!!.last
        states[userId]!!.apply {
            if (currentFrame is ListFrame) previous else previous.show()
        }
    }

    suspend fun home(userId: Long) {
        states[userId]!!.resetStack().show()
        states[userId]!!.resetSession()
    }

    /**
     * Support functions
     */

    private suspend fun createState(navResponse: NavResponse): Boolean {
        return if (!states.containsKey(navResponse.userId)) {
            val state =
                UserState(navResponse.userId)
                    .addLast(
                        createFrame (navResponse.userId) {
                            roots["/home"]?.invoke()
                                ?: error("home frame must be associated with /home command")
                        }
                    )
            states[navResponse.userId] = state
            state.last.handle(navResponse)
            false
        } else true
    }

    fun setNavSession(userId: Long, sessionId: Long?) {
        states[userId]?.let { user ->
            sessionId?.let { id ->
                user.setSession(id)
            } ?: user.resetSession()
        }
    }

    fun getNavSession(userId: Long) = states[userId]?.navSession

    suspend fun repeat(userId: Long) {
        setNavSession(userId, null)
        update(userId)
    }
}

