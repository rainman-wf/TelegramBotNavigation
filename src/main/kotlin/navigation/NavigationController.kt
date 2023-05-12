package navigation

import navigation.args.ArgName
import navigation.args.NavArg
import navigation.frame.*
import navigation.models.NavResponse
import navigation.models.UserId
import navigation.models.UserState

internal object NavigationController {

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

    suspend fun updateHandler(navResponse: NavResponse) {
        if (!createState(navResponse)) return
        if (navResponse.data == (homeKey.type as Home).command) home(navResponse.userId)
        roots[navResponse.data]?.let {
            val frame = frameFactory.create(navResponse.userId, it, null)
            states[navResponse.userId]!!.resetToRoot(frame).show()
        }
        states[navResponse.userId]?.last?.handle(navResponse)
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

    private suspend fun createState(navResponse: NavResponse) : Boolean {
        return if (!states.containsKey(navResponse.userId)) {
            val state = UserState(navResponse.userId).addLast(frameFactory.create(navResponse.userId, homeKey, null))
            states[navResponse.userId] = state
            state.last.handle(navResponse)
            false
        } else true
    }

    fun setNavSession(userId: UserId, sessionId: Long?) {
        states[userId]?.let { user ->
            sessionId?.let { id ->
                user.setSession(id)
            } ?: user.resetSession()
        }
    }

    fun getNavSession(userId: UserId) = states[userId]?.navSession

    suspend fun repeat(userId: UserId) {
        setNavSession(userId, null)
        update(userId)
    }

}

