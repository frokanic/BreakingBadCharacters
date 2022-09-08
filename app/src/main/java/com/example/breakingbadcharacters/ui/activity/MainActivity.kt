package com.example.breakingbadcharacters.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.database.BreakingBadDatabase
import com.example.breakingbadcharacters.databinding.ActivityMainBinding
import com.example.breakingbadcharacters.repository.BreakingBadRepository
import com.example.breakingbadcharacters.ui.fragment.AllCharactersFragment

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMainActivity()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






    }

    private fun initMainActivity() {
        val repository = BreakingBadRepository(BreakingBadDatabase(this))
        val viewModelProviderFactory = MainActivityViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainActivityViewModel::class.java)
    }


}