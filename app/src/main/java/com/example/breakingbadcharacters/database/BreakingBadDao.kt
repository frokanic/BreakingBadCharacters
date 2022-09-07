package com.example.breakingbadcharacters.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.breakingbadcharacters.remote.response.Characters
import com.example.breakingbadcharacters.remote.response.CharactersItem


@Dao
interface BreakingBadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharactersItem)

    @Query("Select * from characters")
    fun showAllDbCharacters(): LiveData<List<CharactersItem>>

    @Delete()
    suspend fun deleteCharacter(character: CharactersItem)

}

/*
@Query("Select * from characters")
    fun showAllDbCharacters(): LiveData<List<Characters>>
 */