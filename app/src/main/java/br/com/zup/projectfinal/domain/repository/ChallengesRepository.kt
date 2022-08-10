package br.com.zup.projectfinal.domain.repository

import br.com.zup.projectfinal.domain.model.ChallengeModel

class ChallengesRepository {
    val listofchallenges = mutableListOf<ChallengeModel>()

    fun setChallengesList(){
        listofchallenges.add(
            ChallengeModel(
                challengeName = "Beba 2 litros de água",
                challengePoints = 20
            )
        )

        listofchallenges.add(
            ChallengeModel(
                challengeName = "Faça um alongamento",
                challengePoints = 50
            ))

        listofchallenges.add(
            ChallengeModel(
                challengeName = "Pausa para o chá/café",
                challengePoints = 15
            ))

        listofchallenges.add(
            ChallengeModel(
                challengeName = "Assista um vídeo engraçado",
                challengePoints = 10
            ))

        listofchallenges.add(
            ChallengeModel(
                challengeName = "Coma algo saudável",
                challengePoints = 70
            ))

        listofchallenges.add(
            ChallengeModel(
                challengeName = "Passe um tempo ao ar livre",
                challengePoints = 60
            ))
    }

    fun getFourRandomChallenges(): List<ChallengeModel>{
        listofchallenges.shuffle()
        val fourChallengesList = mutableListOf<ChallengeModel>()

        for(i in 0..3){
            fourChallengesList.add(listofchallenges[i])
        }

        return fourChallengesList
    }
}