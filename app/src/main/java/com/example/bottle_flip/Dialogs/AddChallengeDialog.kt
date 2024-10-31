package com.example.bottle_flip.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bottle_flip.model.Challenge
import com.example.bottle_flip.R
import com.example.bottle_flip.repository.ChallengeRepository
import kotlinx.coroutines.launch

class AddChallengeDialog(context: Context, private val listener: (Challenge) -> Unit) : Dialog(context) {

    private val challengeRepository = ChallengeRepository(context)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_challenge)
        setCancelable(false)

        //ancho del dialog
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val btnAdd: Button = findViewById(R.id.btnSave)
        val btnCancel: Button = findViewById(R.id.btnCancel)
        val etDescription: EditText = findViewById(R.id.etDescription)

        btnCancel.setOnClickListener {
            dismiss()
        }

        etDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Habilitar el botón solo si hay texto en el campo
                btnAdd.isEnabled = !s.isNullOrEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        btnAdd.setOnClickListener {
            val description = etDescription.text.toString()
            if (description.isNotEmpty()) {
                try {
                    val newChallenge = Challenge(description = description)
                    // Guardar en la base de datos
                    (context as? AppCompatActivity)?.lifecycleScope?.launch {
                        challengeRepository.saveChallenge(newChallenge)
                        listener(newChallenge)
                        dismiss()
                    }
                } catch (e: Exception) {
                    Log.e("AddChallengeDialog", "Error creating challenge: ${e.message}")
                }
            }
        }
    }
}
