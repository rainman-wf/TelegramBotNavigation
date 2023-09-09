package navigation

import navigation.args.NavArg
import navigation.frame.Frame

internal inline fun <reified T : Frame> createFrame(userId: Long, args: NavArg? = null, parentFrame: Frame? = null, constructor: () -> T): T {
    val builder = constructor().setUserId(userId)
    args?.let { builder.setArgs(it) }
    parentFrame?.let { builder.setParentFrame(it) }
    return builder as T
}
