package com.example.bottle_flip.model

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Challenge(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String
): Serializable {
    init {
        Log.d("Model", "Challenge created: $description")
    }
}