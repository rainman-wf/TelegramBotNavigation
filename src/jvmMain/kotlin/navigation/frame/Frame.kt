package navigation.frame

import navigation.NavigationController
import navigation.args.ArgName
import navigation.args.ArgsContainer
import navigation.args.NavArg
import navigation.models.*


abstract class Frame(private val userId: UserId, private val args: ArgsContainer? = null) {

    internal val controller = NavigationController

    fun arg(name: ArgName) = args?.args?.get(name) ?: error ("args not passed")

    abstract suspend fun show()

    suspend fun text(block: (NewMessage) -> Unit) {
        val msgId = controller.getNavSession(userId)
        val builder = NewMessage(userId, msgId)
        block(builder)
        val response = builder.execute()
        controller.setNavSession(userId, response.messageId)
    }

    suspend fun home() = controller.home(userId)

    open suspend fun handle(response: Response) {
        response.messageId?.let { deleteInput(it) }
    }

    private suspend fun deleteInput(messageId: Int) {
        DeleteMessage(userId, messageId).execute()
    }

    suspend fun navigate(key: FrameKey, vararg args: Pair<ArgName, NavArg>) = controller.navigate(userId, key, args)
    open suspend fun back() = controller.back(userId)

    override fun toString(): String = this::class.simpleName.toString()
}




