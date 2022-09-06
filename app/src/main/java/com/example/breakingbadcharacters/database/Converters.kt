package com.example.breakingbadcharacters.database

import androidx.room.TypeConverter
import com.example.breakingbadcharacters.remote.response.CharactersItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun saveIntList(list: List<Int>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromOccupation(listOfStr: List<String>): String {
        var result = ""
        for (str in listOfStr) {
            result += ", $str"
        }
        return result
    }

    @TypeConverter
    fun getIntList(list: String): List<Int> {
        return Gson().fromJson(
            list,
            object : TypeToken<List<Int>>() {}.type
        )
    }


    @TypeConverter
    fun toCharactersItemAppearance(string: String): List<String> {
        return string.split(", ").toList()
    }





}

/*
@TypeConverter
    fun fromCharactersItemAppearance(charactersItem: CharactersItem): String {
        var result = ""
        for (apr in charactersItem.appearance) {
            result += ", $apr"
        }
        return result
    }

    @TypeConverter
    fun fromBetterCallSaulAppearance(charactersItem: CharactersItem): String {
        var result = ""
        for (ocp in charactersItem.better_call_saul_appearance) {
            result += ", $ocp"
        }
        return result
    }

    @TypeConverter
    fun fromOccupation(charactersItem: CharactersItem): String {
        var result = ""
        for (apr in charactersItem.occupation) {
            result += ", $apr"
        }
        return result
    }
 */