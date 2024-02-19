package navigation

import navigation.frame.Frame


class InitRootFrames {

    val frames = mutableMapOf<String, () -> Frame>()

    fun root(command: String, frame: () -> Frame) {
        frames[command] = frame
    }

    fun home(frame: () -> Frame ) {
        frames["/home"] = frame
    }
}

