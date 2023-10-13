package navv2.abstractions

interface Navigatable {

    suspend fun navigate(constructor: () -> Fragment, arg: NavArg? = null)

    suspend fun back()

    suspend fun replace(constructor: () -> Fragment, arg: NavArg? = null)

    suspend fun update()
}