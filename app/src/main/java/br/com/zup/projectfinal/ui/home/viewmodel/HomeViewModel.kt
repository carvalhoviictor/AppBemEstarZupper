package br.com.zup.projectfinal.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import br.com.zup.projectfinal.domain.repository.AuthenticationRepository

class HomeViewModel: ViewModel() {
    private val authenticationRepository = AuthenticationRepository()

    fun getUserName() = authenticationRepository.getNameUser()

    fun logout(){
        authenticationRepository.logout()
    }
}