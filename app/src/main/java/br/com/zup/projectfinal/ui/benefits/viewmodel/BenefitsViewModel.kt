package br.com.zup.projectfinal.ui.benefits.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.projectfinal.domain.model.Benefit
import br.com.zup.projectfinal.domain.repository.BenefitsRepository
import br.com.zup.projectfinal.utils.DIVISOR
import br.com.zup.projectfinal.utils.ERROR

class BenefitsViewModel() : ViewModel() {
    private val repository = BenefitsRepository()
    private val _benefitResponse: MutableLiveData<List<Benefit>> = MutableLiveData()
    val benefitResponse: LiveData<List<Benefit>> = _benefitResponse

    fun getAllBenefits() {
        try {
            _benefitResponse.value = repository.getAllBenefits()

        } catch (ex: Exception) {
            Log.i(ERROR, DIVISOR + ex.message)
        }
    }
}