package com.example.bottle_flip.repository
import android.content.Context
import android.util.Log
import com.example.bottle_flip.data.ChallengeDB
import com.example.bottle_flip.data.ChallengeDao
import com.example.bottle_flip.model.Challenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChallengeRepository(val context: Context){

    private var challengeDao:ChallengeDao = ChallengeDB.getDatabase(context).challengeDao()

    suspend fun saveChallenge(challenge:Challenge){
        withContext(Dispatchers.IO){
            challengeDao.saveChallenge(challenge)
            Log.d("ChallengeRepository", "Challenge saved: description='${challenge.description}'")
        }
    }

    suspend fun getListChallenge():MutableList<Challenge>{
        return withContext(Dispatchers.IO){
            val challenges = challengeDao.getListChallenge()
            Log.d("ChallengeRepository", challenges.toString()) // Imprimir aquí
            challenges
        }
    }

    suspend fun deletechallenge(challenge: Challenge){
        withContext(Dispatchers.IO){
            challengeDao.deleteChallenge(challenge)

        }
    }

    suspend fun updateRepositoy(challenge: Challenge){
        withContext(Dispatchers.IO){
            challengeDao.updateChallenge(challenge)
        }
    }


}