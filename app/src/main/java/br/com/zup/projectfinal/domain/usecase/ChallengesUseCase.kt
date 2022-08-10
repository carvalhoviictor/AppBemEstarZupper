package br.com.zup.projectfinal.domain.usecase

import android.app.Application
import br.com.zup.projectfinal.domain.model.ChallengeModel
import br.com.zup.projectfinal.domain.repository.ChallengesRepository

class ChallengesUseCase(application: Application) {
    val challengesRepository = ChallengesRepository()

    fun setChallengesList(){
        challengesRepository.setChallengesList()
    }

    fun getFourRandomChallenges(): List<ChallengeModel>{
        return challengesRepository.getFourRandomChallenges()
    }
}