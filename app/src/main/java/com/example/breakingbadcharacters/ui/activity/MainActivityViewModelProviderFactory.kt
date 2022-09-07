package com.example.breakingbadcharacters.ui.activity

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.breakingbadcharacters.repository.BreakingBadRepository

class MainActivityViewModelProviderFactory(
    val app: Application,
    val breakingBadRepository: BreakingBadRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(app, breakingBadRepository) as T
    }

}