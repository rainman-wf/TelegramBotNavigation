package bot.poller

import bot.models.Update
import kotlinx.coroutines.flow.Flow

interface Poller {
    fun poll(): Flow<Update>
}