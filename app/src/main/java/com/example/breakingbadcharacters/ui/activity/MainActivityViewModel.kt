package com.example.breakingbadcharacters.ui.activity

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbadcharacters.CharactersApplication
import com.example.breakingbadcharacters.remote.response.Characters
import com.example.breakingbadcharacters.remote.response.CharactersItem
import com.example.breakingbadcharacters.repository.BreakingBadRepository
import com.example.cocktailfinder.common.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainActivityViewModel(
    app: Application,
    val breakingBadRepository: BreakingBadRepository
): AndroidViewModel(app) {

    val characters: MutableLiveData<Resource<Characters>> = MutableLiveData()
    val searchCharacters: MutableLiveData<Resource<Characters>> = MutableLiveData()

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        safeCharactersCall()
    }

    fun searchCharacters(name: String) = viewModelScope.launch {
        safeSearchCharactersCall(name)
    }


    private fun handleCharactersResponse(response: Response<Characters>): Resource<Characters> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeCharactersCall() {
        characters.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = breakingBadRepository.getCharactersList()
                characters.postValue(handleCharactersResponse(response))
            } else {
                characters.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> characters.postValue(Resource.Error("Network Failure"))
                else -> characters.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private suspend fun safeSearchCharactersCall(name: String) {
        searchCharacters.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = breakingBadRepository.searchCharacters(name)
                searchCharacters.postValue(handleCharactersResponse(response))
            } else {
                searchCharacters.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> searchCharacters.postValue(Resource.Error("Network Failure"))
                else -> searchCharacters.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<CharactersApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

    fun saveCharacter(character: CharactersItem) = viewModelScope.launch {
        breakingBadRepository.insertCharacter(character)
    }

    fun getSaveCharacters() = breakingBadRepository.showAllDbCharacters()

    fun deleteCharacter(character: CharactersItem) = viewModelScope.launch {
        breakingBadRepository.deleteCharacter(character)
    }


}

