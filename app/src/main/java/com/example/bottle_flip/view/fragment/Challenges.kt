package com.example.bottle_flip.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottle_flip.R
import com.example.bottle_flip.databinding.ChallengesBinding
import com.example.bottle_flip.viewmodel.ChallengeViewModel
import com.example.bottle_flip.view.adapter.ChallengeAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Challenge : Fragment() {
    private lateinit var binding: ChallengesBinding
    private val challengeViewModel: ChallengeViewModel by viewModels()
    private lateinit var adapter: ChallengeAdapter // Instancia única del adaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ChallengesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        controller()
        observerViewModel()
    }

    override fun onResume() {
        super.onResume()
        // Asegúrate de actualizar la lista cada vez que regreses a este fragmento
        challengeViewModel.getListChallenge()
    }

    private fun setupRecyclerView() {
        adapter = ChallengeAdapter(mutableListOf(), findNavController())
        binding.rvContainerChallenge.layoutManager = LinearLayoutManager(context)
        binding.rvContainerChallenge.adapter = adapter
    }

    private fun controller() {
        binding.icToolbarChallenges.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.fabAddChallenges.setOnClickListener {
            findNavController().navigate(R.id.action_challenges_to_addChangeDialog)
        }
    }

    private fun observerViewModel() {
        challengeViewModel.getListChallenge()
        challengeViewModel.listChallenge.observe(viewLifecycleOwner) { listInventory ->
            adapter.updateData(listInventory) // Actualiza los datos del adaptador
        }
    }
}