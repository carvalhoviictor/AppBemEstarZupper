package br.com.zup.projectfinal.ui.photoscreen.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.com.zup.projectfinal.domain.usecase.PexelsUseCase
import br.com.zup.projectfinal.domain.model.Image
import br.com.zup.projectfinal.domain.repository.AuthenticationRepository
import  br.com.zup.projectfinal.ui.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val authenticationRepository = AuthenticationRepository()

    private val pexelsUseCase = PexelsUseCase(application)
    val pexelsState = MutableLiveData<ViewState<Image>>()

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

    fun logout() {
        authenticationRepository.logout()
    }
}