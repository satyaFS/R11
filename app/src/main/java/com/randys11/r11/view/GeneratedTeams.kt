package com.randys11.r11.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.randys11.r11.model.Match
import com.randys11.r11.model.Player
import com.randys11.r11.ui.theme.Purple200
import com.randys11.r11.ui.theme.Purple700

@Composable
fun GeneratedTeams(generatedTeams:List<Player>, match:Match, index:Int){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp)
        .padding(bottom = 5.dp)
        .background(Color.Black))
    {
        val iconOne = Color(0xFF8cf000)
        val iconTwo = Color(0xFFf0008c)
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFF6300f0)), verticalArrangement = Arrangement.SpaceAround) {
            Text(
                text = "${index +1}",
                textAlign = TextAlign.Start,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = generatedTeams.filter { it.type=="WK" }.chunked(3)){
                        player->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp), Arrangement.SpaceEvenly) {
                        for (team in player){
                            Column(modifier = Modifier.height(50.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    tint =
                                    if(match.teamOne == team.team){
                                        iconTwo
                                    }
                                    else{
                                        iconOne
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(30.dp)

                                )
                                Text(
                                    text = when {
                                        team.isCaptain -> {team.name + " ( C )"}
                                        team.isViceCaptain -> {team.name +" ( Vc )"}
                                        else -> team.name
                                    },
                                    color =
                                    if(team.isCaptain || team.isViceCaptain){
                                        Color.Yellow
                                    }else  Color.White,
                                    fontWeight = FontWeight.Bold
                                    //modifier = Modifier.padding(end = 20.dp),
                                    //overflow = TextOverflow.Visible
                                )
                            }
                        }
                    }
                }

            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = generatedTeams.filter { it.type=="BAT" }.chunked(3)){
                        player->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp), Arrangement.SpaceEvenly) {
                        for (team in player){
                            Column(modifier = Modifier.height(50.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    tint =
                                    if(match.teamOne == team.team){
                                        iconTwo

                                    }
                                    else{
                                        iconOne
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(30.dp)

                                )
                                Text(
                                    text = when {
                                        team.isCaptain -> {team.name + " ( C )"}
                                        team.isViceCaptain -> {team.name +" ( Vc )"}
                                        else -> team.name
                                    },
                                    color =
                                    if(team.isCaptain || team.isViceCaptain){
                                        Color.Yellow
                                    }else  Color.White,

                                    fontWeight = FontWeight.Bold

                                    //modifier = Modifier.padding(end = 20.dp),
                                    //overflow = TextOverflow.Visible
                                )
                            }
                        }
                    }
                }
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = generatedTeams.filter { it.type=="ALL" }.chunked(3)){
                        player->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp), Arrangement.SpaceEvenly) {
                        for (team in player){
                            Column(modifier = Modifier.height(50.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    tint =
                                    if(match.teamOne == team.team){
                                       iconTwo
                                    }
                                    else{
                                       iconOne
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(30.dp)

                                )
                                Text(
                                    text = when {
                                        team.isCaptain -> {team.name + " ( C )"}
                                        team.isViceCaptain -> {team.name +" ( Vc )"}
                                        else -> team.name
                                    },
                                    color =
                                    if(team.isCaptain || team.isViceCaptain){
                                        Color.Yellow
                                    }else  Color.White,
                                    fontWeight = FontWeight.Bold
                                    //modifier = Modifier.padding(end = 20.dp),
                                    //overflow = TextOverflow.Visible
                                )
                            }
                        }
                    }
                }
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = generatedTeams.filter { it.type=="BOWL" }.chunked(3)){
                        player->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp), Arrangement.SpaceEvenly) {
                        for (team in player){
                            Column(modifier = Modifier.height(50.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    tint =
                                    if(match.teamOne == team.team){
                                        iconTwo
                                    }
                                    else{
                                        iconOne
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(30.dp)

                                )
                                Text(
                                    text = when {
                                        team.isCaptain -> {team.name + " ( C )"}
                                        team.isViceCaptain -> {team.name +" ( Vc )"}
                                        else -> team.name
                                    },
                                    color =
                                    if(team.isCaptain || team.isViceCaptain){
                                        Color.Yellow
                                    }else  Color.White,
                                    fontWeight = FontWeight.Bold
                                    //modifier = Modifier.padding(end = 20.dp),
                                    //overflow = TextOverflow.Visible
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//Compose generates teams as cards
@Composable
fun LazyTeams(list: List<List<Player>>, match: Match){
    val bool = remember{ mutableStateOf(true) }
    val openDialog = remember{ mutableStateOf(false) }

    BackHandler(enabled = bool.value, onBack = {openDialog.value = true})


    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),contentPadding = PaddingValues(1.dp)
    ){
        itemsIndexed(list){index,player->
            GeneratedTeams(player, match, index)
        }

    }
    if (openDialog.value ) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                openDialog.value = false
            },
            title = {
                Text(text = "Hope you have created your Teams !!")
            },
            text = {
                Text(
                    "If you go back now, you will lose your generated teams"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        bool.value = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text("Cancel")
                }
            }

        )
    }
}