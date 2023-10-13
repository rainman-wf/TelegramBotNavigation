package navv2.abstractions

import navv2.entities.UserAction

interface Updatable {

    val userId: Long
    suspend fun handle(action: UserAction)
}