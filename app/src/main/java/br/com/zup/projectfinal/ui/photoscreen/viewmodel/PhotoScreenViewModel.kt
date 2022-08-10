package br.com.zup.projectfinal.ui.photoscreen.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import br.com.zup.projectfinal.data.SingleLiveEvent
import br.com.zup.projectfinal.domain.PexelsUseCase
import br.com.zup.projectfinal.domain.model.Image
import  br.com.zup.projectfinal.ui.ViewState

class PhotoScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val pexelsUseCase = PexelsUseCase(application)
    val pexelsState = SingleLiveEvent<ViewState<Image>>()

    fun getImage() {

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    pexelsUseCase.getImageNetwork()
                }
                pexelsState.value = response
            } catch (ex: Exception) {
                pexelsState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a imagem vinda da internet!"))
            }
        }
    }


}