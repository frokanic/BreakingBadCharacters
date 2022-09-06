package com.example.breakingbadcharacters.remote

import com.example.breakingbadcharacters.remote.response.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreakingBadApi {

    @GET("/api/characters")
    suspend fun getCharactersList(): Response<Characters>


    /* Space is represented by a 'plus sign' between the first and last name.
       This query only works with the full name of a character. Double check your spelling!
       For example, name=Walter+White returns exactly Walter White, where name=Walter returns everyone with Walter in their name */
    @GET("/api/characters?{name}")
    suspend fun getCharacterSearch(
        @Path("name") name: String
    ): Response<Characters>

}