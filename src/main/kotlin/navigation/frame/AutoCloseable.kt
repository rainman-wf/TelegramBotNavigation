package navigation.frame

interface AutoCloseable {
    val timeout: Int get() = 60
    val removeCurrent: Boolean get() = true

}