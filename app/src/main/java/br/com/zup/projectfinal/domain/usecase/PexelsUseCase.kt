package br.com.zup.projectfinal.domain.usecase

import android.app.Application
import br.com.zup.projectfinal.domain.repository.PexelsRepository
import br.com.zup.projectfinal.domain.model.Image
import br.com.zup.projectfinal.ui.viewstate.ViewState

class PexelsUseCase(application: Application) {
    private val repository = PexelsRepository()

    suspend fun getImageNetwork(): ViewState<Image> {
        return try {
            val response = repository.getImages()
            ViewState.Success(response.random())
        } catch (ex: Exception) {
            ViewState.Error(ex)
        }
    }

}

