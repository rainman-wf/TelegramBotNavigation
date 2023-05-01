package botapi.poller

import botapi.models.Update

data class PollingUpdatesResult(
    val ok: Boolean,
    val result: List<Update>
)