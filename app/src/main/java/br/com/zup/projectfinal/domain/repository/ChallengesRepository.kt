package br.com.zup.projectfinal.domain.repository

import br.com.zup.projectfinal.domain.model.ChallengeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.ktx.Firebase

class ChallengesRepository {
    private val authentication: FirebaseAuth = Firebase.auth

    private val listofchallenges = mutableListOf<ChallengeModel>()
    private val database = FirebaseDatabase.getInstance()
    private val referencePoints =
        database.getReference("bemEstarZupper/${authentication.currentUser?.uid}/Pontuacao")
    private val referenceLevel =
        database.getReference("bemEstarZupper/${authentication.currentUser?.uid}/Nivel")

    fun databaseReferencePoints() = referencePoints
    fun databaseReferenceLevel() = referenceLevel

    fun getLevel(): Query {
        return referenceLevel.orderByValue()
    }

    fun getPoints(): Query {
        return referencePoints.orderByValue()
    }

    fun setChallengesList() {
        listofchallenges.add(ChallengeModel(challengeName = "Beba 2 litros de água", challengePoints = 20))
        listofchallenges.add(ChallengeModel(challengeName = "Faça alongamentos ao longo do dia", challengePoints = 50))
        listofchallenges.add(ChallengeModel(challengeName = "Pausa para o chá/café", challengePoints = 20))
        listofchallenges.add(ChallengeModel(challengeName = "Assista a um vídeo engraçado", challengePoints = 10))
        listofchallenges.add(ChallengeModel(challengeName = "Equilibre sua dieta com uma alimentação saudável", challengePoints = 30))
        listofchallenges.add(ChallengeModel(challengeName = "Dê uma volta pelo seu bairro após o almoço", challengePoints = 60))
        listofchallenges.add(ChallengeModel(challengeName = "Faça acompanhamento nutricional", challengePoints = 80))
        listofchallenges.add(ChallengeModel(challengeName = "Programe um tempo de lazer aos finais de semana", challengePoints = 60))
        listofchallenges.add(ChallengeModel(challengeName = "Dê prioridade para a sua saúde mental", challengePoints = 100))
        listofchallenges.add(ChallengeModel(challengeName = "Pratique atividades físicas para hábitos saudáveis (no mínimo 2 vezes por semana)", challengePoints = 50))
        listofchallenges.add(ChallengeModel(challengeName = "Não pule refeições", challengePoints = 40))
        listofchallenges.add(ChallengeModel(challengeName = "Faça pausas regulares e programadas", challengePoints = 60))
        listofchallenges.add(ChallengeModel(challengeName = "Programe sua rotina. Faça listas", challengePoints = 30))
        listofchallenges.add(ChallengeModel(challengeName = "Faça caminhadas regulares", challengePoints = 90))
        listofchallenges.add(ChallengeModel(challengeName = "Trace metas pequenas", challengePoints = 40))
        listofchallenges.add(ChallengeModel(challengeName = "Mora perto da praia? Vai correr por lá!", challengePoints = 40))
        listofchallenges.add(ChallengeModel(challengeName = "Procure por algum hobby", challengePoints = 60))
        listofchallenges.add(ChallengeModel(challengeName = "Reduza o consumo de alimentos industrializados e de fast food", challengePoints = 100))
        listofchallenges.add(ChallengeModel(challengeName = "Tire aquele plano para uma vida mais saudável do papel", challengePoints = 80))
        listofchallenges.add(ChallengeModel(challengeName = " Mantenha a casa sempre limpa", challengePoints = 60))
        listofchallenges.add(ChallengeModel(challengeName = "Coma menos carne vermelha", challengePoints = 50))
        listofchallenges.add(ChallengeModel(challengeName = "Crie expectativas positivas. É de graça", challengePoints = 30))
        listofchallenges.add(ChallengeModel(challengeName = "Gaste mais tempo com quem você gosta", challengePoints = 40))
    }

    fun getFourRandomChallenges(): List<ChallengeModel> {
        listofchallenges.shuffle()
        val fourChallengesList = mutableListOf<ChallengeModel>()

        for (i in 0..3) {
            fourChallengesList.add(listofchallenges[i])
        }

        return fourChallengesList
    }
}