package bot.poller

import bot.models.Update

data class PollingUpdatesResult(
    val ok: Boolean,
    val result: List<Update>
)