package br.com.zup.projectfinal.domain.model

data class ChallengeModel(
    var challengeName: String,
    var challengePoints: Int,
    var check: Boolean = false,
    var position: Int = 6
)