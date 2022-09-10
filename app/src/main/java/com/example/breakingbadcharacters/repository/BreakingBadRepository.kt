package com.example.breakingbadcharacters.repository


import com.example.breakingbadcharacters.database.BreakingBadDatabase
import com.example.breakingbadcharacters.remote.RetrofitInstance
import com.example.breakingbadcharacters.remote.response.Characters
import com.example.breakingbadcharacters.remote.response.CharactersItem
import retrofit2.Response


class BreakingBadRepository(
    val db: BreakingBadDatabase
) {

    suspend fun getCharactersList(): Response<Characters> {
        return RetrofitInstance.api.getCharactersList()
    }

    suspend fun searchCharacters(name: String): Response<Characters> {
        return RetrofitInstance.api.getCharacterSearch(name)
    }

    suspend fun insertCharacter(character: CharactersItem) = db.getCharacterDao().insertCharacter(character)

    fun showAllDbCharacters() = db.getCharacterDao().showAllDbCharacters()

    suspend fun deleteCharacter(character: CharactersItem) = db.getCharacterDao().deleteCharacter(character)



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