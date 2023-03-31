package navigation.frame

import navigation.NavigationController
import navigation.args.ArgName
import navigation.args.ArgsContainer
import navigation.args.NavArg
import navigation.models.*

abstract class Frame(private val userId: UserId, private val args: ArgsContainer? = null) {

    internal val controller = NavigationController

    private var result: NavArg? = null

    @Suppress("UNCHECKED_CAST")
    fun <T : NavArg?> getResult(): T? {
        return result as T?
    }

    private suspend fun putResult(arg: NavArg) {
        result = arg
    }

    private suspend fun parentFrame(): Frame = controller.parentFrame(userId)

    @Suppress("UNCHECKED_CAST")
    fun <T: NavArg>arg(name: ArgName) = args?.args?.get(name) as T

    abstract suspend fun show()

    suspend fun text(block: NewMessage.() -> Unit) {
        val msgId = controller.getNavSession(userId)
        val builder = NewMessage(userId, msgId)
        block(builder)
        val response = builder.execute() ?: return
        controller.setNavSession(userId, response.messageId)
    }

    suspend fun chain(vararg block: NewMessage.() -> Unit) {
        block.forEach {
            val builder = NewMessage(userId, 0)
            it(builder)
            builder.execute()
        }
    }

    suspend fun home() = controller.home(userId)

    suspend fun popUp(callbackId: String, block: PopUpMsg.() -> Unit) {
        val builder = PopUpMsg(callbackId, userId)
        block(builder)
        builder.execute()
    }

    open suspend fun handle(response: Response) {
        response.messageId?.let { deleteInput(it) }
    }

    private suspend fun deleteInput(messageId: Int) {
        DeleteMessage(userId, messageId).execute()
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T : NavArg> navigateForResult(
        key: FrameKey,
        vararg args: Pair<ArgName, NavArg>,
    ) {
        controller.navigate(userId, key, args)
    }

    suspend fun update() {
        controller.update(userId)
    }

    suspend fun backWithResult(arg: NavArg) {
        parentFrame().putResult(arg)
        controller.back(userId)
    }

    suspend fun navigate(key: FrameKey, vararg args: Pair<ArgName, NavArg>) = controller.navigate(userId, key, args)
    suspend fun back() = controller.back(userId)

    override fun toString(): String = this::class.simpleName.toString()
}




