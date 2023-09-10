package botapi.sender.api

import botapi.models.BaseResponse
import botapi.models.File
import botapi.models.UserProfilePhotos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataApi {

    @GET("getUserProfilePhotos")
    suspend fun getUserProfilePhotos(
        @Query("user_id") userId: Long,
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): Response<BaseResponse<UserProfilePhotos>>


    @GET("getFile")
    suspend fun getFile(
        @Query("file_id") fileId: String
    ): Response<BaseResponse<File>>
}