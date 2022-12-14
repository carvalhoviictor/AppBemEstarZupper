package br.com.zup.projectfinal.domain.repository

import br.com.zup.projectfinal.data.datasource.remote.RetrofitService
import br.com.zup.projectfinal.data.datasource.remote.model.Photo
import br.com.zup.projectfinal.domain.model.Image

class PexelsRepository {

    suspend fun getImages(): List<Image> {
        val resp = RetrofitService.apiService.getImagesPexels(
            RetrofitService.KEY_API,
            "healthy"
        )
        return toListImage(resp.photos)
    }

    private fun toListImage(photos: List<Photo>): List<Image> {
        val images: MutableList<Image> = mutableListOf<Image>()

        for (i in photos) {
            images.add(
                Image(
                    alt = i.alt,
                    src = i.src.tiny
                )
            )
        }
        images.shuffle()
        return images
    }
}