package navigation.frame

import navigation.models.UserId
import navigation.args.ArgName
import navigation.args.ArgsContainer
import navigation.args.NavArg

interface FrameFactory {
    fun create(userId: UserId, key: FrameKey, args: Array<out Pair<ArgName, NavArg>>?): Frame

    fun withArgs(args: Array<out Pair<ArgName, NavArg>>) : ArgsContainer {
        return ArgsContainer(args.associate { it.first to it.second })
    }
}

