package navv2.abstractions

interface LifeCycle {
    suspend fun onCreate()
    suspend fun onStart()
    suspend fun onDestroy()
}