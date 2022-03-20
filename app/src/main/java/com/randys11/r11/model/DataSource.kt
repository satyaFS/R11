package com.randys11.r11.model

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.collections.HashMap

class DataSource {
    var db = Firebase.firestore

    private val ref = db.collection("Matches")

    suspend  fun loadMatches():List<Match>{
        val matches = mutableListOf<Match>()
        val som = ref.document("45727").get()
        som.addOnSuccessListener {
            Log.d(TAG, "${it.data}")
        }.
        addOnFailureListener { exception ->
            Log.d(TAG, "Error getting documents: ", exception)
        }



        ref.get()
            .addOnSuccessListener { docs ->
                for (d in docs){
                    Log.d(TAG, "${d.id} => ${d.data}")
//                    val m = Match(d["league"].toString(),d["teamOne"].toString(), d["teamTwo"].toString(),
//                        d["teamOneSC"].toString(),d["teamTwoSC"].toString(),d["startTime"].toString()
//                    )
                    val match:Match = d.toObject()
                    match.matchID = d.id.toString()
                    matches.add(match)
//                    Log.d(TAG, "$m")
//                    Log.d(TAG, "${matches.size}, after m")
                }

            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
            .await()

        Log.d("who is fast", "${matches.size}")
        Log.d(TAG, "${matches.size}, after block")
        // loadPlayers() //comment it
        return matches
    }

    //to load players for a given match
    suspend fun loadPlayers(matchId:String):List<Player>{
        var count = 1
        val listOfPlayers = mutableListOf<Player>()
        ref.document(matchId).collection("Players")
            .get().addOnSuccessListener {players->
                for(p in players) {
                    while (p[count.toString()] != null) {
                        val hashMap: Map<String, String> =
                            p[count.toString()] as HashMap<String, String>
                        listOfPlayers.add(
                            Player(
                                if(hashMap["name"] != null) {hashMap["name"] } else "",
                                hashMap["team"],
                                hashMap["salary"],
                                hashMap["points"],
                                hashMap["lastMatch"].toBoolean(),
                                hashMap["type"]
                            )
                        )
                        count++
                        Log.d("who is hash", "$hashMap")
                    }
                }

                Log.d("who is hash", "$listOfPlayers")
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
            .await()
        return  listOfPlayers
    }


// Add a new document with a generated ID

}