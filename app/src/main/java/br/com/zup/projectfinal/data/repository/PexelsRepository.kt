package br.com.zup.projectfinal.data.repository

import android.util.Log
import br.com.zup.projectfinal.data.RetrofitService
import br.com.zup.projectfinal.data.model.Photo
import br.com.zup.projectfinal.domain.model.Image
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class PexelsRepository {

    suspend fun getImages(): List<Image> {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitService.apiService.getImagesPexels("query=frutas&query=verduras")
            }
            return toListImage(response.photos)
        } catch (ex: Exception) {
            Log.i("Error", "Error ----- > ${ex.message}")
        }
        return listOf<Image>(
            Image(
                alt = "",
                src = ""
            )
        )
    }

    private fun toListImage(photos: List<Photo>): List<Image> {
        var images: MutableList<Image> = mutableListOf<Image>()

        for (i in photos) {
            images.add(
                Image(
                    alt = i.alt,
                    src = i.src.original
                )
            )
        }
        return images
    }


}
