package navigation

import navigation.frame.Frame
import navigation.frame.HomeFrame
import navigation.frame.StartFrame


class InitRootFrames {

    val frames = mutableMapOf<String, () -> Frame>()
    lateinit var start: () -> StartFrame
    lateinit var home: () -> HomeFrame

    fun root(command: String, frame: () -> Frame) {
        frames[command] = frame
    }

    fun home(frame: () -> HomeFrame ) {
        home = frame
    }

    fun start(frame: () -> StartFrame) {
        start = frame
    }
}

