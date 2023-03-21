package navigation.frame

import navigation.NavigationController
import navigation.args.ArgName
import navigation.args.NavArg
import navigation.models.DeleteMessage
import navigation.models.NewMessage
import navigation.models.Response
import navigation.models.UserId


abstract class Frame(private val userId: UserId) {

    internal val controller = NavigationController

    private val _args = mutableMapOf<ArgName, NavArg>()

    fun arg(name: ArgName) = _args[name]

    fun withArgs(vararg navArgEntry: Pair<ArgName, NavArg>): Frame {
        _args.putAll(navArgEntry.associate { it.first to it.second })
        return this
    }

    abstract suspend fun show()

    suspend fun text(block: (NewMessage) -> Unit) {
        val msgId = controller.getNavSession(userId)
        val builder = NewMessage(userId, msgId)
        block(builder)
        val response = builder.execute()
        controller.setNavSession(userId, response.messageId)
    }

    suspend fun clear() {
        val msgId = controller.getNavSession(userId) ?: return
        val builder = DeleteMessage(userId, msgId)
        builder.execute()
        controller.setNavSession(userId, null)
    }

    open suspend fun handle(response: Response) {
        response.messageId?.let { deleteInput(it) }
    }

    suspend fun home() = controller.home(userId)

    private suspend fun deleteInput(messageId: Int) {
        DeleteMessage(userId, messageId).execute()
    }

    suspend fun navigate(key: FrameKey, vararg args: Pair<ArgName, NavArg>) = controller.navigate(userId, key, args)
    open suspend fun back() = controller.back(userId)

    override fun toString(): String = this::class.simpleName.toString()
}




