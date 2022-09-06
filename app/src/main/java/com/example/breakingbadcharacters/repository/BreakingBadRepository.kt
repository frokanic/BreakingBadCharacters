package com.example.breakingbadcharacters.repository


import com.example.breakingbadcharacters.database.BreakingBadDatabase
import com.example.breakingbadcharacters.remote.BreakingBadApi
import com.example.breakingbadcharacters.remote.RetrofitInstance
import com.example.breakingbadcharacters.remote.response.Characters
import com.example.cocktailfinder.common.Resource
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject


class BreakingBadRepository(
    val db: BreakingBadDatabase
) {

    suspend fun getCharactersList(): Response<Characters> {
        return RetrofitInstance.api.getCharactersList()
    }

    suspend fun searchCharacters(name: String): Response<Characters> {
        return RetrofitInstance.api.getCharacterSearch(name)
    }



}

/*

suspend fun getCharactersList(limit: Int, offset: Int): Resource<Characters> {
        val response = try {
            api.getCharactersList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }


suspend fun getSpecificCharacter(characterName: String): Resource<Characters> {
        val response = try {
            api.getCharacterSearch(characterName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }
 */