package br.com.zup.projectfinal.data.datasource.remote

import br.com.zup.projectfinal.data.datasource.remote.model.PexelsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PexelsAPI {
    @GET("search")
    suspend fun getImagesPexels(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String
    ): PexelsResponse
}
