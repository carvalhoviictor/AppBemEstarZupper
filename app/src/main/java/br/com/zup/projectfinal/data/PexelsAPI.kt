package br.com.zup.projectfinal.data

import br.com.zup.projectfinal.data.model.PexelsResponse
import retrofit2.http.GET


interface PexelsAPI {

    @GET("search?query=frutas&query=verduras")
    suspend fun getImagesPexels(): PexelsResponse

}