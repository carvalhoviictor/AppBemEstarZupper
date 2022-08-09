package br.com.zup.projectfinal.data.datasource.remote

import br.com.zup.projectfinal.data.datasource.remote.RetrofitService.Companion.KEY_API
import br.com.zup.projectfinal.data.datasource.remote.model.PexelsResponse
import retrofit2.http.*

interface PexelsAPI {
    @Headers("Authorization $KEY_API ")
    @GET("search")
    suspend fun getImagesPexels(
        @Query("query") query: String
    ): PexelsResponse
}
