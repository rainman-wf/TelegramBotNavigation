package navigation

import navigation.args.ArgName
import navigation.args.NavArg
import navigation.frame.FrameFactory
import navigation.frame.FrameKey
import navigation.models.Response
import navigation.models.UserId
import navigation.models.UserState

object NavigationController {

    private val states = mutableMapOf<UserId, UserState>()

    private lateinit var homeKey: FrameKey
    private lateinit var frameFactory: FrameFactory

    fun attachFactory(frameFactory: FrameFactory, key: FrameKey) {
        this.frameFactory = frameFactory
        homeKey = key
    }

    suspend fun updateHandler(response: Response) {
        checkState(response.userId)
        states[response.userId]?.last?.handle(response)
    }

    suspend fun navigate(userId: UserId, key: FrameKey, args: Array<out Pair<ArgName, NavArg>>? = null) {
        val frame = frameFactory.create(userId, key, args)
        frame.show()
        states[userId]!!.addLast(frame)
    }

    suspend fun back(userId: UserId) {
        states[userId]!!.previous.show()
    }

    fun backFromList(userId: UserId) {
        states[userId]!!.previous
    }

    suspend fun home(userId: UserId) {
        states[userId]!!.resetStack().show()
    }

    // Support functions

    private fun checkState(userId: UserId) {
        if (!states.containsKey(userId)) states[userId] =
            UserState(userId).addLast(frameFactory.create(userId, homeKey, null))
    }

    fun setNavSession(userId: UserId, messageId: Int?) {
        states[userId]?.navSession = messageId
    }

    fun getNavSession(userId: UserId) = states[userId]?.navSession

}

