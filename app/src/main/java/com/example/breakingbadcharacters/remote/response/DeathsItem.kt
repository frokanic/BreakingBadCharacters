package com.example.breakingbadcharacters.remote.response


data class DeathsItem(
    val cause: String,
    val death: String,
    val death_id: Int,
    val episode: Int,
    val last_words: String,
    val number_of_deaths: Int,
    val responsible: String,
    val season: Int
)