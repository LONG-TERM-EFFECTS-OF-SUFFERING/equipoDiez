package com.example.bottle_flip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.bottle_flip.databinding.ItemChallengeBinding
import com.example.bottle_flip.model.Challenge

class ChallengeAdapter(private val challengeList: MutableList<Challenge>) : RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding = ItemChallengeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val challenge = challengeList[position]
        holder.bind(challenge)
    }

    override fun getItemCount(): Int = challengeList.size

    fun addChallenge(challenge: Challenge) {
        challengeList.add(0, challenge)
        notifyItemInserted(0) // Coloca el nuevo reto al principio de la lista
    }

    inner class ChallengeViewHolder(private val binding: ItemChallengeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(challenge: Challenge) {
            binding.description.text = challenge.description

            // Animaciones sutiles en los botones de editar y eliminar
            binding.btnEdit.setOnClickListener {
                animateTouch(binding.btnEdit)
                // Lógica para abrir el diálogo de edición
            }

            binding.btnDelete.setOnClickListener {
                animateTouch(binding.btnDelete)
                // Lógica para abrir el diálogo de confirmación de eliminación
            }
        }

        private fun animateTouch(view: View) {
            val animation = AlphaAnimation(1f, 0.7f)
            animation.duration = 100
            animation.repeatMode = AlphaAnimation.REVERSE
            view.startAnimation(animation)
        }
    }
}
