package com.randys11.r11

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randys11.r11.model.DataSource
import com.randys11.r11.model.Match
import com.randys11.r11.model.Player
import kotlinx.coroutines.launch

class PlayerViewModel:ViewModel() {
    private lateinit var _generatedTeams:List<List<Player>>
    val generatedTeams get() = _generatedTeams
    var selectedPlayers = mutableStateListOf<Player>()
    var selectedMatch = Match()
    var removedPlayers = mutableStateListOf<Player>()
    var list = mutableStateListOf<Match>()
    var loading = mutableStateOf(false)
    var noOfTeams = 1

        private set

    init {
        // selectedPlayers.addAll(Player().loadPlayers())

        viewModelScope.launch {
            loading.value = true
            list.addAll(DataSource().loadMatches())
            loading.value = false
        }



    }

    //    fun loadMatches(){
//        loading.value = true
//        Log.d("who is fast", "${list.size}")
//
//
//        Log.d("who is fast", "${list.size}")
//        loading.value = false
//    }
    fun validateSelectedPlayers():Int{
        return when {
            selectedPlayers.count() <11 -> 0
            selectedPlayers.filter { it.team == selectedMatch.teamOne}.count() < 4 -> 2
            selectedPlayers.filter { it.team == selectedMatch.teamTwo}.count() <4 -> 3
            selectedPlayers.filter { it.type == "WK" }.count() <1 -> 4
            selectedPlayers.filter { it.type == "BAT" }.count() <3 -> 5
            selectedPlayers.filter { it.type == "ALL" }.count() <1 -> 6
            selectedPlayers.filter { it.type == "BOWL" }.count() <3 -> 7
            selectedPlayers.filter { it.isPlayerLocked == true }.count()>11 ->8
            else -> 1
        }
    }

    fun loadPlayers(match:Match){
        selectedPlayers.clear()
        removedPlayers.clear()
        selectedMatch = match
        viewModelScope.launch {
            loading.value = true
            selectedPlayers.addAll(DataSource().loadPlayers(match.matchID ))
            loading.value = false
        }
    }

    fun addPlayer(player:Player){
        selectedPlayers.add(0,player)
        removedPlayers.remove(player)
    }

    fun removePlayer(player: Player){
        selectedPlayers.remove(player)
        removedPlayers.add(0,player)
        player.removeState()
    }

    fun generateTeams(list:List<Player>, noOfTeams:Int){
        _generatedTeams = Player().random11(list, noOfTeams)
    }

    fun LockPlayer( player: Player, truth:Boolean){
        player.lockPlayer(truth)
    }

    fun LockCvc(player: Player, truth:Boolean){
        player.lockCvc(truth)
    }

}