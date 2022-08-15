package br.com.zup.projectfinal.domain.usecase

import android.app.Application
import br.com.zup.projectfinal.domain.model.DailyChallenges
import br.com.zup.projectfinal.domain.repository.AuthenticationRepository
import br.com.zup.projectfinal.domain.repository.ChallengesRepository
import br.com.zup.projectfinal.utils.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

class ChallengesUseCase(application: Application) {
    private val challengesRepository = ChallengesRepository()
    private val authenticationRepository = AuthenticationRepository()

    fun getFourRandomChallenges(): DailyChallenges {
        val challengeList = challengesRepository.getFourRandomChallenges()

        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val currentDate = dateTimeFormat.format(date)

        return DailyChallenges(
            chOneName = challengeList[0].challengeName,
            chOnePoint = challengeList[0].challengePoints,
            checkOne = false,
            chTwoName = challengeList[1].challengeName,
            chTwoPoint = challengeList[1].challengePoints,
            checkTwo = false,
            chThreeName = challengeList[2].challengeName,
            chThreePoint = challengeList[2].challengePoints,
            checkThree = false,
            chFourName = challengeList[3].challengeName,
            chFourPoint = challengeList[3].challengePoints,
            checkFour = false,
            currentDate = currentDate
        )
    }

    fun getUserName(): String {
        return authenticationRepository.getNameUser()
    }
}