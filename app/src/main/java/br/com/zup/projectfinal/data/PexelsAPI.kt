package br.com.zup.projectfinal.data

import br.com.zup.projectfinal.data.RetrofitService.Companion.KEY_API
import br.com.zup.projectfinal.data.model.PexelsResponse
import retrofit2.http.*
import java.security.Key

interface PexelsAPI {
    @Headers("Authorization $KEY_API ")
    @GET("search")
    suspend fun getImagesPexels(
        @Query("query") query: String
    ): PexelsResponse
}
