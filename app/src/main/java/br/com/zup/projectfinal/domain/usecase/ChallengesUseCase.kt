package br.com.zup.projectfinal.domain.usecase

import android.app.Application
import br.com.zup.projectfinal.domain.model.ChallengeModel
import br.com.zup.projectfinal.domain.repository.ChallengesRepository
import br.com.zup.projectfinal.ui.viewstate.ViewState
import br.com.zup.projectfinal.utils.CHALLENGES_LIST_ERROR

class ChallengesUseCase(application: Application) {
    val challengesRepository = ChallengesRepository()

    fun setChallengesList(){
        challengesRepository.setChallengesList()
    }

    fun getFourRandomChallenges(): ViewState<List<ChallengeModel>>{
        return try {
            ViewState.Success(challengesRepository.getFourRandomChallenges())
        }catch (e: Exception){
            ViewState.Error(Exception(CHALLENGES_LIST_ERROR))
        }
    }
}