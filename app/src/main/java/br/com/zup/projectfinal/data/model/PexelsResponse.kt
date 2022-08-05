package br.com.zup.projectfinal.data.model

data class PexelsResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)