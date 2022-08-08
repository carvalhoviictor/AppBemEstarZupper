package br.com.zup.projectfinal.data

import br.com.zup.projectfinal.data.model.PexelsResponse
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.Query

interface PexelsAPI {
    @GET("search")
    suspend fun getImagesPexels(
        @Header("Authorization") auth: String,
        @Query("query") query: String
    ): PexelsResponse
}
