package br.com.zup.projectfinal.domain.model

data class DailyChallenges(
    var chOneName: String,
    var chOnePoint: Int,
    var checkOne: Boolean,

    var chTwoName: String,
    var chTwoPoint: Int,
    var checkTwo: Boolean,

    var chThreeName: String,
    var chThreePoint: Int,
    var checkThree: Boolean,

    var chFourName: String,
    var chFourPoint: Int,
    var checkFour: Boolean,

    var currentDate: String
    )