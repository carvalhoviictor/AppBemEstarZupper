package br.com.zup.projectfinal.ui.challenges.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.projectfinal.domain.model.ChallengeModel
import br.com.zup.projectfinal.domain.repository.AuthenticationRepository
import br.com.zup.projectfinal.domain.usecase.ChallengesUseCase
import br.com.zup.projectfinal.ui.viewstate.ViewState
import br.com.zup.projectfinal.utils.CHALLENGES_LIST_ERROR

class ChallengesViewModel(application: Application) : AndroidViewModel(application) {
    private val authenticationRepository = AuthenticationRepository()
    private val challengesUseCase = ChallengesUseCase(application)
    private var _challengesListState = MutableLiveData<ViewState<List<ChallengeModel>>>()
    val challengesListState: LiveData<ViewState<List<ChallengeModel>>> = _challengesListState

    fun getUserName() = authenticationRepository.getNameUser()

    fun logout() {
        authenticationRepository.logout()
    }

    fun setChallengesList() {
        challengesUseCase.setChallengesList()
    }

    fun getFourRandomChallenges() {
        try {
            _challengesListState.value = challengesUseCase.getFourRandomChallenges()
        } catch (e: Exception) {
            _challengesListState.value = ViewState.Error(Throwable(CHALLENGES_LIST_ERROR))
        }
    }
}