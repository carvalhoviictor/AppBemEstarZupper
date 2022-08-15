package br.com.zup.projectfinal.ui.photoscreen.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.projectfinal.domain.model.Image
import br.com.zup.projectfinal.domain.usecase.PexelsUseCase
import br.com.zup.projectfinal.ui.viewstate.ViewState
import br.com.zup.projectfinal.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class PhotoScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val pref: SharedPreferences =
        application.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)
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
        val dateSave = pref.getString(DATE_KEY, "")
        val imageSave = pref.getString(SRC_KEY, "")

        if (dateSave.isNullOrEmpty() || imageSave.isNullOrEmpty()) return false

        if (dateSave != getCurrentDate()) return false

        return true
    }

    private fun getImagePref() {
        viewModelScope.launch {

            val image = ViewState.Success(
                Image(
                    alt = pref.getString(ALT_KEY, "").toString(),
                    src = pref.getString(SRC_KEY, "").toString()
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
                        Throwable(IMAGE_NETWORK_ERROR)
                    }
                }
            } catch (ex: Exception) {
                pexelsState.value =
                    ViewState.Error(Throwable(IMAGE_LOADING_ERROR))
            }
        }
    }

    private fun saveImagePref(image: Image) {
        prefEditor.putString(DATE_KEY, getCurrentDate())
        prefEditor.putString(ALT_KEY, image.alt)
        prefEditor.putString(SRC_KEY, image.src)
        prefEditor.apply()
    }

    private fun getCurrentDate(): String {
        val date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat(FORMAT_DATE, Locale.getDefault())
        return dateFormat.format(date)
    }
}