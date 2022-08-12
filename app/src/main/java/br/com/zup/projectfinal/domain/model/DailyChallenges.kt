package br.com.zup.projectfinal.domain.model

data class DailyChallenges(
    var chOneName: String,
    var chOnePoint: Int,

    var chTwoName: String,
    var chTwoPoint: Int,

    var chThreeName: String,
    var chThreePoint: Int,

    var chFourName: String,
    var chFourPoint: Int,

    var currentDate: String
    )