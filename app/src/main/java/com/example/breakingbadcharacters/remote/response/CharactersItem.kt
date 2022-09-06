package com.example.breakingbadcharacters.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(
    tableName = "characters"
)
data class CharactersItem(
    val appearance: List<Int>,
    val better_call_saul_appearance: List<Int>,
    val birthday: String,
    val category: String,
    @PrimaryKey
    val char_id: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val portrayed: String,
    val status: String,
    var saved: Boolean = false           //Added by me
): Serializable