package com.example.breakingbadcharacters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.databinding.FragmentDeathDetailsBinding
import com.example.breakingbadcharacters.ui.activity.MainActivity
import com.example.breakingbadcharacters.ui.activity.MainActivityViewModel


class DeathDetailsFragment : Fragment(R.layout.fragment_death_details) {

    private lateinit var binding: FragmentDeathDetailsBinding
    lateinit var viewModel: MainActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeathDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

}