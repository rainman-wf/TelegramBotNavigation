package navigation.frame

import navigation.models.UserId
import navigation.args.ArgName
import navigation.args.NavArg

interface FrameFactory {
    fun create(userId: UserId, key: FrameKey, args: Array<out Pair<ArgName, NavArg>>?): Frame
}

