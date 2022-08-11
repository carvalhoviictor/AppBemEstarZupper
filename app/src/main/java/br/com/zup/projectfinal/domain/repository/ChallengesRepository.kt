package br.com.zup.projectfinal.domain.repository

import android.net.Uri
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
        return referencePoints.orderByValue()
    }

    private fun setChallengesList(): List<ChallengeModel>{

        val listofchallenges = mutableListOf<ChallengeModel>()

        listofchallenges.add(
            ChallengeModel(
                challengeName = "Beba 2 litros de água",
                challengePoints = 20
            )
        )

        listofchallenges.add(ChallengeModel(
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

        return listofchallenges
    }

    fun getFourRandomChallenges(): List<ChallengeModel>{
        val listofchallenges = setChallengesList().toMutableList()

        listofchallenges.shuffle()
        val fourChallengesList = mutableListOf<ChallengeModel>()

        for(i in 0..3){
            fourChallengesList.add(listofchallenges[i])
        }

        return fourChallengesList
    }
}