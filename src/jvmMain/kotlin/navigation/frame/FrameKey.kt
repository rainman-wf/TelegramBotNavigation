package navigation.frame

interface FrameKey {
    val type: Type? get() = null
}

interface Type

interface TypeWithCommand : Type{
    val command: String?
}

class Final : Type

data class Root(
    override val command: String
) : TypeWithCommand

data class Home(
    override val command: String? = null
) : TypeWithCommand

