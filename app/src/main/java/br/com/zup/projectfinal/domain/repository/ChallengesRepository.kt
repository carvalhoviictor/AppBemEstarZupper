package br.com.zup.projectfinal.domain.repository

import br.com.zup.projectfinal.domain.model.ChallengeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.ktx.Firebase

class ChallengesRepository {
    private val authentication: FirebaseAuth = Firebase.auth
    private val database = FirebaseDatabase.getInstance()

    private val referencePoints = database.getReference("bemEstarZupper/${authentication.currentUser?.uid}/Pontuacao" )
    private val referenceLevel = database.getReference("bemEstarZupper/${authentication.currentUser?.uid}/Nivel" )
    private val referenceUsername = database.getReference("bemEstarZupper/${authentication.currentUser?.uid}/NomeUsuario")

    fun databaseReferencePoints() = referencePoints
    fun databaseReferenceLevel() = referenceLevel
    fun databaseReferenceUsername() = referenceUsername

    fun getLevel(): Query {
        return referenceLevel.orderByValue()
    }

    fun getPoints(): Query {
        return referencePoints
    }

    private fun setChallengesList(): List<ChallengeModel> {
    val listofchallenges = mutableListOf<ChallengeModel>()
        listofchallenges.add(ChallengeModel(challengeName = "Beba 2L de água", challengePoints = 20))
        listofchallenges.add(ChallengeModel(challengeName = "Faça alongamento", challengePoints = 50))
        listofchallenges.add(ChallengeModel(challengeName = "Pausa para o chá\nou café", challengePoints = 15))
        listofchallenges.add(ChallengeModel(challengeName = "Assista um vídeo\nengraçado", challengePoints = 10))
        listofchallenges.add(ChallengeModel(challengeName = "Faça uma refeição\nsaudável", challengePoints = 25))
        listofchallenges.add(ChallengeModel(challengeName = "Dê uma volta em\nseu bairro", challengePoints = 60))
        listofchallenges.add(ChallengeModel(challengeName = "Faça acompanhamento\nnutricional", challengePoints = 80))
        listofchallenges.add(ChallengeModel(challengeName = "Tire um tempo\npara seu lazer", challengePoints = 60))
        listofchallenges.add(ChallengeModel(challengeName = "Priorize a sua\nsaúde mental", challengePoints = 100))
        listofchallenges.add(ChallengeModel(challengeName = "Pratique uma\natividade física", challengePoints = 55))
        listofchallenges.add(ChallengeModel(challengeName = "Não pule refeições", challengePoints = 40))
        listofchallenges.add(ChallengeModel(challengeName = "Faça pausas regulares", challengePoints = 70))
        listofchallenges.add(ChallengeModel(challengeName = "Programe sua rotina", challengePoints = 30))
        listofchallenges.add(ChallengeModel(challengeName = "Faça uma caminhada", challengePoints = 90))
        listofchallenges.add(ChallengeModel(challengeName = "Trace metas pequenas", challengePoints = 45))
        listofchallenges.add(ChallengeModel(challengeName = "Mora perto da praia?\nVai correr por lá!", challengePoints = 40))
        listofchallenges.add(ChallengeModel(challengeName = "Procure por algum\nhobby", challengePoints = 60))
        listofchallenges.add(ChallengeModel(challengeName = "Reduza o consumo\nde alimentos\nindustrializados e de\nfast food", challengePoints = 95))
        listofchallenges.add(ChallengeModel(challengeName = "Tire aquele plano\npara uma vida mais\nsaudável do papel", challengePoints = 85))
        listofchallenges.add(ChallengeModel(challengeName = "Mantenha sua\ncasa limpa", challengePoints = 75))
        listofchallenges.add(ChallengeModel(challengeName = "Coma menos carne\nvermelha", challengePoints = 50))
        listofchallenges.add(ChallengeModel(challengeName = "Crie expectativas\npositivas", challengePoints = 35))
        listofchallenges.add(ChallengeModel(challengeName = "Gaste mais tempo com\nquem você gosta!", challengePoints = 40))
        return listofchallenges  
    }

    fun getFourRandomChallenges(): List<ChallengeModel>{
        val listofchallenges = setChallengesList().toMutableList()

        listofchallenges.shuffle()
        val fourChallengesList = mutableListOf<ChallengeModel>()

        for (i in 0..3) {
            fourChallengesList.add(listofchallenges[i])
        }

        return fourChallengesList
    }
}