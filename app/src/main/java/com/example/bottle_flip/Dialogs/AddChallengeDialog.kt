package com.example.bottle_flip.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import com.example.bottle_flip.model.Challenge
import com.example.bottle_flip.R

class AddChallengeDialog(context: Context, private val listener: (Challenge) -> Unit) : Dialog(context) {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_challenge)

        val btnAdd: Button = findViewById(R.id.btnAddChallenge)
        val etDescription: EditText = findViewById(R.id.etDescription)

        btnAdd.setOnClickListener {
            val description = etDescription.text.toString()
            if (description.isNotEmpty()) {
                listener(Challenge(description = description))
                dismiss()
            }
        }
    }
}
