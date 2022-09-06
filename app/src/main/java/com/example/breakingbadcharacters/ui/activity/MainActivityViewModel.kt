package com.example.breakingbadcharacters.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbadcharacters.remote.response.Characters
import com.example.breakingbadcharacters.remote.response.CharactersItem
import com.example.breakingbadcharacters.repository.BreakingBadRepository
import com.example.cocktailfinder.common.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel(
    val breakingBadRepository: BreakingBadRepository
): ViewModel() {

    val characters: MutableLiveData<Resource<Characters>> = MutableLiveData()


    val searchCharacters: MutableLiveData<Resource<Characters>> = MutableLiveData()

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        characters.postValue(Resource.Loading())
        val response = breakingBadRepository.getCharactersList()
        characters.postValue(handleCharactersResponse(response))
    }

    fun searchCharacters(name: String) = viewModelScope.launch {
        searchCharacters.postValue(Resource.Loading())
        val response = breakingBadRepository.searchCharacters(name)
        characters.postValue(handleCharactersResponse(response))
    }


    private fun handleCharactersResponse(response: Response<Characters>): Resource<Characters> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    /*
    private fun handleSearchCharactersResponse(response: Response<Characters>): Resource<Characters> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
     */


}

