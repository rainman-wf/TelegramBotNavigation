package navigation

import navigation.args.NavArg
import navigation.frame.*
import navigation.models.NavResponse
import navigation.models.UserState

internal object NavigationController {

    private val states = mutableMapOf<Long, UserState>()
    private val roots = mutableMapOf<String, () -> Frame>()
    lateinit var start: () -> StartFrame
    lateinit var home: () -> HomeFrame

    val inHomeStates get() = states.filterValues { it.inHome }

    /** base functions */

    fun initBaseFrames(frames: Map<String, () -> Frame>) {
        roots.putAll(frames)
    }

    fun initHomeFrame(homeFrame: () -> HomeFrame) {
        home = homeFrame
    }

    fun initStartFrame(frame: () -> StartFrame) {
        start = frame
    }

    suspend fun updateHandler(navResponse: NavResponse) {
        createState(navResponse)

        when {

            navResponse.data == "/home" -> home(navResponse.userId)

            navResponse.data.matches("/start( [A-Za-zА-Яа-яЁё0-9_]{1,256}$)?".toRegex()) ->
                states[navResponse.userId]!!
                    .resetToRoot(createFrame(navResponse.userId, getStartArg(navResponse.data)) { start.invoke() })
                    .show()

            else -> roots[navResponse.data]?.let {
                states[navResponse.userId]!!
                    .resetToRoot(createFrame(navResponse.userId) { it.invoke() })
                    .show()
            }
        }

        states[navResponse.userId]?.last?.handle(navResponse)
    }


    /** navigation functions */

    suspend fun navigate(userId: Long, constructor: () -> Frame, args: NavArg? = null) {
        val frame = createFrame(userId, args) { constructor() }

        val state = states[userId]!!

        if (frame is FinalFrame) {
            frame.show()
            state.resetSession()
            state.resetStack()
        } else {
            state.addLast(frame)
            frame.show()
        }
    }

    suspend fun navigate(userId: Long, constructor: () -> Frame, args: NavArg? = null, parent: Frame?) {
        val frame = createFrame(userId, args, parent) { constructor() }

        val state = states[userId]!!

        if (frame is FinalFrame) {
            frame.show()
            state.resetSession()
            state.resetStack()
        } else {
            state.addLast(frame)
            frame.show()
        }
    }


    suspend fun back(userId: Long) {
        states[userId]!!.apply { previous.show() }
    }

    suspend fun home(userId: Long) {
        states[userId]!!.home().show()
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


    suspend fun repeat(userId: Long) {
        setNavSession(userId, null)
        update(userId)
    }

    suspend fun next(userId: Long, constructor: () -> Frame, args: NavArg? = null) {
        val frame = createFrame(userId, args) { constructor() }

        frame.show()

        setNavSession(userId, null)
        states[userId]!!.resetStack()
        navigate(userId, constructor, args)
    }

    /**
     * Support functions
     */

    private fun createState(navResponse: NavResponse) {
        if (!states.containsKey(navResponse.userId)) {
            val state =
                UserState(navResponse.userId, home().setUserId(navResponse.userId) as HomeFrame)
            states[navResponse.userId] = state
        }
    }

    fun setNavSession(userId: Long, sessionId: Long?) {
        states[userId]?.let { user ->
            sessionId?.let { id ->
                user.setSession(id)
            } ?: user.resetSession()
        }
    }

    fun getNavSession(userId: Long) = states[userId]?.navSession
    suspend fun back(userId: Long, steps: Int) {

        if (steps > 1) {
            repeat(steps - 1) {
                states[userId]!!.deleteStackItem()
            }
            states[userId]!!.last
        }

        states[userId]!!.apply { previous.show() }
    }

    private fun getStartArg(string: String): StartFrame.Args? {
        println(string)
        return if (string == "/start") null
        else StartFrame.Args(string.substringAfter("/start "))
    }
}

