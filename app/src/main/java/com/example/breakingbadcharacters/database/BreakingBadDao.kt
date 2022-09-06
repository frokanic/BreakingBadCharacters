package com.example.breakingbadcharacters.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.breakingbadcharacters.remote.response.Characters


@Dao
interface BreakingBadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Characters)


    @Delete()
    suspend fun deleteCharacter(character: Characters)

}

/*
@Query("Select * from characters")
    fun showAllDbCharacters(): LiveData<List<Characters>>
 */