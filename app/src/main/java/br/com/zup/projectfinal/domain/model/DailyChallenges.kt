package br.com.zup.projectfinal.domain.model

data class DailyChallenges(
    var chOneName: String,
    var chOnePoint: Int,
    var checkOne: Boolean = false,

    var chTwoName: String,
    var chTwoPoint: Int,
    var checkTwo: Boolean = false,

    var chThreeName: String,
    var chThreePoint: Int,
    var checkThree: Boolean = false,

    var chFourName: String,
    var chFourPoint: Int,
    var checkFour: Boolean = false,

    var currentDate: String
)