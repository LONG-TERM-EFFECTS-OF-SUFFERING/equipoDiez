package com.example.bottle_flip

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottle_flip.dialogs.AddChallengeDialog
import com.example.bottle_flip.databinding.ActivityChallengeBinding


class ChallengeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChallengeBinding
    private var audioIsPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setBackgroundColor(resources.getColor(R.color.dark_gray))

        setupToolbar()
        setupRecyclerView()
        setupFloatingActionButton()
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
