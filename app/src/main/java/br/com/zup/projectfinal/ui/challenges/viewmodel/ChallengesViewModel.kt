package br.com.zup.projectfinal.ui.challenges.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.projectfinal.domain.model.ChallengeModel
import br.com.zup.projectfinal.domain.model.DailyChallenges
import br.com.zup.projectfinal.domain.repository.AuthenticationRepository
import br.com.zup.projectfinal.domain.repository.ChallengesRepository
import br.com.zup.projectfinal.domain.usecase.ChallengesUseCase
import br.com.zup.projectfinal.ui.viewstate.ViewState
import br.com.zup.projectfinal.utils.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChallengesViewModel(application: Application) : AndroidViewModel(application) {
    private val authenticationRepository = AuthenticationRepository()
    private val challengesUseCase = ChallengesUseCase(application)
    private val challengesRepository = ChallengesRepository()

    private var _challengesListState = MutableLiveData<ViewState<List<ChallengeModel>>>()
    val challengesListState: LiveData<ViewState<List<ChallengeModel>>> = _challengesListState

    private var _msgState = MutableLiveData<String>()
    val msgState: LiveData<String> = _msgState

    private var _levelState = MutableLiveData<List<String>>()
    val levelState: LiveData<List<String>> = _levelState

    private var _pointsState = MutableLiveData<List<String>>()
    val pointsState: LiveData<List<String>> = _pointsState

    var challengePosition: Int = 6

    fun logout() {
        authenticationRepository.logout()
    }

    fun getFourRandomChallenges() {
        try {
            val dailyChallenges = challengesUseCase.getFourRandomChallenges()

            if(pref.getString(PREF_CH_ONE_NAME_KEY,"").toString().isEmpty()){
                saveChallenges(dailyChallenges)
                _challengesListState.value = getSavedChallenges()
            }else{
                if(dailyChallenges.currentDate == pref.getString(PREF_DATE_KEY,"").toString()){
                    _challengesListState.value = getSavedChallenges()

                }else{
                    saveChallenges(dailyChallenges)
                    _challengesListState.value = getSavedChallenges()
                }
            }

        } catch (e: Exception) {
            _challengesListState.value = ViewState.Error(Throwable(CHALLENGES_LIST_ERROR))
        }
    }

    fun getUserName(): String{
        return challengesUseCase.getUserName()
    }

    fun savePoints(point: Int) {
        val pointsPath = getPointsPath(point)
        challengesRepository.databaseReferencePoints().child("$pointsPath")
            .setValue(point) { error, reference ->
                if (error != null) {
                    _msgState.value = error.message
                }
                _msgState.value = CONGRATULATION
            }
    }

    private fun getPointsPath(point: Int): String {
        val uri = Uri.parse(point.toString())
        return uri.toString()
    }

    fun saveLevel(level: Int) {
        val levelPath = getLevelPath(level)
        challengesRepository.databaseReferenceLevel().child("$levelPath")
            .setValue(level) { error, reference ->
                if (error != null) {
                    _msgState.value = error.message
                }
            }
    }

    private fun getLevelPath(level: Int): String {
        val uri = Uri.parse(level.toString())
        return uri.toString()
    }

    fun getLevelDatabase() {
        challengesRepository.getLevel().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val levelList = mutableListOf<String>()
                for (resultSnapshot in snapshot.children) {
                    val levelResponse = resultSnapshot.value.toString()
                    levelResponse.let {
                        levelList.add(it)
                    }
                }
                _levelState.value = levelList
            }

            override fun onCancelled(error: DatabaseError) {
                _msgState.value = error.message
            }
        })
    }

    fun getPointsDatabase() {
        challengesRepository.getPoints().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val pointsList = mutableListOf<String>()
                for (resultSnapshot in snapshot.children) {
                    val pointsResponse = resultSnapshot.value.toString()
                    pointsResponse.let {
                        pointsList.add(it)
                    }
                }
                _pointsState.value = pointsList
            }

            override fun onCancelled(error: DatabaseError) {
                _msgState.value = error.message
            }
        })
    }

    //############ SETAR NOME DE USUÁRIO NO REALTIME DATABASE DO FIREBASE ############

    private fun getUserNamePath(username: String): String {
        val uri = Uri.parse(username)
        return uri.toString()
    }

    fun saveUsernameFirebaseDatabase(username: String) {
        val usernamePath = getUserNamePath(username)
        challengesRepository.databaseReferenceUsername().child(usernamePath)
            .setValue(username) { error, _ ->
                if (error != null) {
                    _msgState.value = error.message
                }
            }
    }

    //############ SHARED PREFERENCES ############
    private val pref: SharedPreferences = application.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
    private val prefEditor: SharedPreferences.Editor = pref.edit()

    private fun saveChallenges(challenges: DailyChallenges){
        try {
            prefEditor.putString(PREF_DATE_KEY, challenges.currentDate)

            prefEditor.putString(PREF_CH_ONE_NAME_KEY, challenges.chOneName)
            prefEditor.putInt(PREF_CH_ONE_POINT_KEY, challenges.chOnePoint)
            prefEditor.putBoolean(PREF_CHECKED_ONE, challenges.checkOne)

            prefEditor.putString(PREF_CH_TWO_NAME_KEY, challenges.chTwoName)
            prefEditor.putInt(PREF_CH_TWO_POINT_KEY, challenges.chTwoPoint)
            prefEditor.putBoolean(PREF_CHECKED_TWO, challenges.checkTwo)

            prefEditor.putString(PREF_CH_THREE_NAME_KEY, challenges.chThreeName)
            prefEditor.putInt(PREF_CH_THREE_POINT_KEY, challenges.chThreePoint)
            prefEditor.putBoolean(PREF_CHECKED_THREE, challenges.checkThree)

            prefEditor.putString(PREF_CH_FOUR_NAME_KEY, challenges.chFourName)
            prefEditor.putInt(PREF_CH_FOUR_POINT_KEY, challenges.chFourPoint)
            prefEditor.putBoolean(PREF_CHECKED_FOUR, challenges.checkFour)

            prefEditor.apply()
        }catch (e: Exception){
            _msgState.value = e.message
        }
    }

    private fun getSavedChallenges(): ViewState<List<ChallengeModel>>{
        return try {
            val challengesList = mutableListOf<ChallengeModel>()

            challengesList.add(
                ChallengeModel(
                    pref.getString(PREF_CH_ONE_NAME_KEY,"").toString(),
                    pref.getInt(PREF_CH_ONE_POINT_KEY,0),
                    pref.getBoolean(PREF_CHECKED_ONE, false)
                ))

            challengesList.add(
                ChallengeModel(
                    pref.getString(PREF_CH_TWO_NAME_KEY,"").toString(),
                    pref.getInt(PREF_CH_TWO_POINT_KEY,0),
                    pref.getBoolean(PREF_CHECKED_TWO, false)
                ))

            challengesList.add(
                ChallengeModel(
                    pref.getString(PREF_CH_THREE_NAME_KEY,"").toString(),
                    pref.getInt(PREF_CH_THREE_POINT_KEY,0),
                    pref.getBoolean(PREF_CHECKED_THREE, false)
                ))

            challengesList.add(
                ChallengeModel(
                    pref.getString(PREF_CH_FOUR_NAME_KEY,"").toString(),
                    pref.getInt(PREF_CH_FOUR_POINT_KEY,0),
                    pref.getBoolean(PREF_CHECKED_FOUR, false)
                ))
            ViewState.Success(challengesList)
        }catch (e: Exception){
            ViewState.Error(Exception(CHALLENGES_LIST_ERROR))
        }
    }

    fun updateCheck(){
        if(challengePosition == 0){
            prefEditor.putBoolean(PREF_CHECKED_ONE, true)
        }
        if(challengePosition == 1){
            prefEditor.putBoolean(PREF_CHECKED_TWO, true)
        }
        if(challengePosition == 2){
            prefEditor.putBoolean(PREF_CHECKED_THREE, true)
        }
        if(challengePosition == 3){
            prefEditor.putBoolean(PREF_CHECKED_FOUR, true)
        }
    }
}