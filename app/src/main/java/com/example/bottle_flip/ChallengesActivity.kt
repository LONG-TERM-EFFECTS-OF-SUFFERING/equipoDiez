package com.example.bottle_flip

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottle_flip.dialogs.AddChallengeDialog
import com.example.bottle_flip.databinding.ActivityChallengeBinding
import com.example.bottle_flip.repository.ChallengeRepository
import com.example.bottle_flip.adapter.ChallengeAdapter
import kotlinx.coroutines.launch


class ChallengeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChallengeBinding
    private lateinit var challengeRepository: ChallengeRepository
    private lateinit var challengeAdapter: ChallengeAdapter
    private var audioIsPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setBackgroundColor(resources.getColor(R.color.dark_gray))

        challengeRepository = ChallengeRepository(this)
        challengeAdapter = ChallengeAdapter(mutableListOf())

        setupToolbar()
        setupRecyclerView()
        setupFloatingActionButton()
        loadChallenges()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Retos"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            if (audioIsPlaying) resumeAudio()
            finish()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewChallenges.apply {
            layoutManager = LinearLayoutManager(this@ChallengeActivity)
            adapter = ChallengeAdapter(mutableListOf()) // Lista vacía inicial
        }
    }

    private fun setupFloatingActionButton() {
        binding.fabAddChallenge.setOnClickListener {
            AddChallengeDialog(this) { newChallenge ->
                (binding.recyclerViewChallenges.adapter as ChallengeAdapter).addChallenge(newChallenge)
            }.show()
        }
    }

    private fun loadChallenges() {
        lifecycleScope.launch {
            val challenges = challengeRepository.getListChallenge()
            challengeAdapter.updateChallenges(challenges)
        }
    }

    private fun isAudioPlaying(): Boolean {
        // Lógica para verificar si el audio está en modo ON
        return false // Placeholder
    }

    private fun pauseAudio() {
        // Pausar el audio si está en modo ON
    }

    private fun resumeAudio() {
        // Reanudar el audio si estaba en modo ON
    }
}
