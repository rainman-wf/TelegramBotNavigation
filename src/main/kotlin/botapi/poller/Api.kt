package botapi.poller

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("getUpdates")
    suspend fun getUpdates(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?,
        @Query("timeout") timeout: Int?,
        @Query("allowed_updates") allowedUpdates: String?
    ): Response<PollingUpdatesResult>
}

