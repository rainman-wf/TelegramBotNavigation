package navigation

import navigation.args.ArgName
import navigation.args.NavArg
import navigation.frame.FrameFactory
import navigation.frame.FrameKey
import navigation.frame.ListFrame
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
        homeKey = keys.singleOrNull { it.isHome } ?: error("home frame not set")
        roots.putAll(
            keys.filter { it.isRoot }.associateBy { it.command ?: error("root frame should has unique command") }
        )
    }

    suspend fun updateHandler(response: Response) {
        checkState(response.userId)
        if (response.data == homeKey.command) home(response.userId)
        roots[response.data]?.let {
            val frame = frameFactory.create(response.userId, it, null)
            states[response.userId]!!.resetToRoot(frame).show()
        }
        states[response.userId]?.last?.handle(response)
    }

    suspend fun navigate(userId: UserId, key: FrameKey, args: Array<out Pair<ArgName, NavArg>>? = null) {
        val frame = frameFactory.create(userId, key, args)
        frame.show()
        states[userId]!!.addLast(frame)
    }

    suspend fun back(userId: UserId) {
        val currentFrame = states[userId]!!.last
        states[userId]!!.apply {
            if (currentFrame is ListFrame) previous else previous.show()
        }
    }

    suspend fun home(userId: UserId) {
        states[userId]!!.resetStack().show()
    }

    /**
     * Support functions
     */

    private fun checkState(userId: UserId) {
        if (!states.containsKey(userId)) states[userId] =
            UserState(userId).addLast(frameFactory.create(userId, homeKey, null))
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

