package navigation

import navigation.args.ArgName
import navigation.args.NavArg
import navigation.frame.*
import navigation.models.Response
import navigation.models.UserId
import navigation.models.UserState

object NavigationController {

    private val states = mutableMapOf<UserId, UserState>()

    private lateinit var homeKey: FrameKey
    private lateinit var frameFactory: FrameFactory
    private val roots = mutableMapOf<String, FrameKey>()

    fun attachFactory(frameFactory: FrameFactory, keys: List<FrameKey>) {
        this.frameFactory = frameFactory
        homeKey = keys.singleOrNull { it.type is Home } ?: error("home frame not set")
        roots.putAll(
            keys.filter { it.type is Root }.associateBy { (it.type as Root).command }
        )
    }

    suspend fun updateHandler(response: Response) {
        if (!createState(response)) return
        if (response.data == (homeKey.type as Home).command) home(response.userId)
        roots[response.data]?.let {
            val frame = frameFactory.create(response.userId, it, null)
            states[response.userId]!!.resetToRoot(frame).show()
        }
        states[response.userId]?.last?.handle(response)
    }

    suspend fun navigate(userId: UserId, key: FrameKey, args: Array<out Pair<ArgName, NavArg>>? = null) {
        val frame = frameFactory.create(userId, key, args)
        frame.show()

        val state = states[userId]!!

        if (key.type is Final) {
            state.resetSession()
            state.resetStack()
        } else {
            state.addLast(frame)
        }
    }

    suspend fun update(userId: UserId) {
        log("update")
        states[userId]!!.last.show()
    }

    suspend fun parentFrame(userId: UserId) : Frame {
        return states[userId]!!.parent()
    }

    suspend fun back(userId: UserId) {
        val currentFrame = states[userId]!!.last
        states[userId]!!.apply {
            if (currentFrame is ListFrame) previous else previous.show()
        }
    }

    suspend fun home(userId: UserId) {
        states[userId]!!.resetStack().show()
        states[userId]!!.resetSession()
    }

    /**
     * Support functions
     */

    private suspend fun createState(response: Response) : Boolean {
        return if (!states.containsKey(response.userId)) {
            val state = UserState(response.userId).addLast(frameFactory.create(response.userId, homeKey, null))
            states[response.userId] = state
            state.last.handle(response)
            false
        } else true
    }

    fun setNavSession(userId: UserId, sessionId: Int?) {
        states[userId]?.let { user ->
            sessionId?.let { id ->
                user.setSession(id)
            } ?: user.resetSession()
        }
    }

    fun getNavSession(userId: UserId) = states[userId]?.navSession

}

