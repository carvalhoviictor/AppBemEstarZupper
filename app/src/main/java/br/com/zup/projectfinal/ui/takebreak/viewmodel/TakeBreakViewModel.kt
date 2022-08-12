package br.com.zup.projectfinal.ui.takebreak.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.projectfinal.domain.model.TakeBreakModel
import br.com.zup.projectfinal.domain.repository.TakeBreakRepository
import br.com.zup.projectfinal.utils.DIVISOR
import br.com.zup.projectfinal.utils.ERROR

class TakeBreakViewModel () : ViewModel() {
    private val repository = TakeBreakRepository()
    private val _takeBreakResponse: MutableLiveData<List<TakeBreakModel>> = MutableLiveData()
    val takeBreakResponse: LiveData<List<TakeBreakModel>> = _takeBreakResponse

    fun getTakeBreak() {
        try {
            _takeBreakResponse.value = repository.getTakeBreakList()

        } catch (ex: Exception) {
            Log.i(ERROR, DIVISOR + ex.message)
        }
    }
}