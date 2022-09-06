package com.example.breakingbadcharacters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.databinding.FragmentCharacterDetailsBinding
import com.example.breakingbadcharacters.ui.activity.MainActivity
import com.example.breakingbadcharacters.ui.activity.MainActivityViewModel


class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private lateinit var binding: FragmentCharacterDetailsBinding
    lateinit var viewModel: MainActivityViewModel
    val args: CharacterDetailsFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setContext()
    }


    private fun setContext() {
        val character = args.character

        Glide.with(this).load(character.img).into(binding.ivCharacterImage)
        binding.tvName.text = character.name
        binding.tvNickname.text = "Nickname: ${character.nickname}"
        binding.tvOccupation.text = "Occupation: ${character.occupation}"
        binding.tvBirthday.text = "Birthday: ${character.birthday}"

        if (character.status == "Deceased") {
            binding.ivDeceased.visibility = View.VISIBLE
        }
        if (character.category.contains("Breaking Bad")) {
            binding.ivBreakingBad.visibility = View.VISIBLE
        }
        if (character.category.contains("Better Call Saul")) {
            binding.ivBetterCallSaul.visibility = View.VISIBLE
        }


    }




}