package navigation

import botapi.models.User
import navigation.args.NavArg
import navigation.frame.Frame

internal inline fun <reified T : Frame> createFrame(user: User, args: NavArg? = null, parentFrame: Frame? = null, constructor: () -> T): T {
    val builder = constructor().setUser(user)
    args?.let { builder.setArgs(it) }
    parentFrame?.let { builder.setParentFrame(it) }
    return builder as T
}
