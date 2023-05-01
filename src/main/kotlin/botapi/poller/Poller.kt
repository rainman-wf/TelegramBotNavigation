package botapi.poller

import botapi.models.Update
import kotlinx.coroutines.flow.Flow

interface Poller {
    fun poll(): Flow<Update>
}