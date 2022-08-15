package br.com.zup.projectfinal.ui.photoscreen.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.projectfinal.domain.model.Image
import br.com.zup.projectfinal.domain.repository.AuthenticationRepository
import br.com.zup.projectfinal.domain.usecase.PexelsUseCase
import br.com.zup.projectfinal.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class PhotoScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val pref: SharedPreferences =
        application.getSharedPreferences("Shared", Context.MODE_PRIVATE)
    private val prefEditor: SharedPreferences.Editor = pref.edit()

    private val pexelsUseCase = PexelsUseCase(application)
    val pexelsState = MutableLiveData<ViewState<Image>>()

    fun getImage() {

        if (checkImageDay()) {
            getImagePref()
        } else {
            getImageRepository()
        }
    }

    private fun checkImageDay(): Boolean {
        val dateSave = pref.getString("DATE", "")
        val imageSave = pref.getString("SRC", "")

        if (dateSave.isNullOrEmpty() || imageSave.isNullOrEmpty()) return false

        if (dateSave != getCurrentDate()) return false

        return true
    }

    private fun getImagePref() {
        viewModelScope.launch {

            val image = ViewState.Success(
                Image(
                    alt = pref.getString("ALT", "").toString(),
                    src = pref.getString("SRC", "").toString()
                )
            )
            pexelsState.value = image
        }
    }

    private fun getImageRepository() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    pexelsUseCase.getImageNetwork()
                }
                pexelsState.value = response

                when (response) {
                    is ViewState.Success<*> -> {
                        saveImagePref(response.data as Image)
                    }
                    is ViewState.Error -> {
                        Throwable("Não foi possível salvar  a imagem vinda da internet!")
                    }
                }
            } catch (ex: Exception) {
                pexelsState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a imagem vinda da internet!"))
            }
        }
    }

    private fun saveImagePref(image: Image) {
        prefEditor.putString("DATE", getCurrentDate())
        prefEditor.putString("ALT", image.alt)
        prefEditor.putString("SRC", image.src)
        prefEditor.apply()
    }


    private fun getCurrentDate(): String {
        val date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return dateFormat.format(date)
    }
}