package com.example.breakingbadcharacters.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.adapter.AllCharactersAdapter
import com.example.breakingbadcharacters.adapter.SavedCharactersAdapter
import com.example.breakingbadcharacters.databinding.FragmentAllCharactersBinding
import com.example.breakingbadcharacters.ui.activity.MainActivity
import com.example.breakingbadcharacters.ui.activity.MainActivityViewModel
import com.example.cocktailfinder.common.Constants.SEARCH_TIME_DELAY
import com.example.cocktailfinder.common.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AllCharactersFragment : Fragment(R.layout.fragment_all_characters) {

    private lateinit var binding: FragmentAllCharactersBinding
    lateinit var viewModel: MainActivityViewModel
    lateinit var allCharactersAdapter: AllCharactersAdapter
    lateinit var savedCharactersAdapter: SavedCharactersAdapter
    val TAG = "AllCharactersFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupAllCharactersRecyclerView()
        setupSavedCharactersRecyclerView()



        viewModel.characters.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { charactersResponse ->
                        allCharactersAdapter.differ.submitList(charactersResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        var job: Job? = null
        binding.svMainSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchCharacters(editable.toString())
                    }
                }
            }
        }

        viewModel.searchCharacters.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { charactersResponse ->
                        allCharactersAdapter.differ.submitList(charactersResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        viewModel.getSaveCharacters().observe(viewLifecycleOwner, Observer { characters ->
            savedCharactersAdapter.differ.submitList(characters)
        })

    }

    private fun setupAllCharactersRecyclerView() {
        allCharactersAdapter = AllCharactersAdapter()
        binding.rvAllCharacters.apply {
            adapter = allCharactersAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        allCharactersAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("character", it)
            }
            findNavController().navigate(
                R.id.action_allCharactersFragment_to_characterDetailsFragment,
                bundle
            )
        }

    }

    private fun setupSavedCharactersRecyclerView() {
        savedCharactersAdapter = SavedCharactersAdapter()
        binding.rvSavedCharacters.apply {
            adapter = savedCharactersAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        savedCharactersAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("character", it)
            }
            findNavController().navigate(
                R.id.action_allCharactersFragment_to_characterDetailsFragment,
                bundle
            )
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }


}

