package com.example.breakingbadcharacters.database

import android.content.Context
import androidx.room.*
import com.example.breakingbadcharacters.remote.response.CharactersItem


@Database(
    entities = [CharactersItem::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BreakingBadDatabase: RoomDatabase() {

    abstract fun getCharacterDao(): BreakingBadDao

    companion object {
        @Volatile
        private var instance: BreakingBadDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BreakingBadDatabase::class.java,
                "characters_db.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}