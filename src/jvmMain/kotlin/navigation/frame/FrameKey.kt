package navigation.frame

interface FrameKey {
    val command: String? get () = null
    val isHome: Boolean get () = false
    val isRoot: Boolean get () = false
}