package navigation.frame

import navigation.args.NavArg

abstract class StartFrame : Frame() {

    class Args (
        val value: String
    ): NavArg

    fun startArgs() : Args? {
        return navArgs<Args>()
    }

}