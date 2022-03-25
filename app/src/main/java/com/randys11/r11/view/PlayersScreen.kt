package com.randys11.r11.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.randys11.r11.model.Match
import com.randys11.r11.model.Player

@Composable
fun PlayerCard(
    player: Player,
    onPlayerRemoved: (Player) -> Unit,
    match: Match
    // lockPlayer:(player:Player, truth:Boolean)->Unit,
    //lockCvc:(player:Player, truth:Boolean)->Unit
) {
    Column( //shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            //.padding(bottom = 5.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
        // .border(Dp.Hairline, Color.Gray),
        // border = BorderStroke(0.5.dp, Color.Black)
        // elevation = 0.dp
    )
    {

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 4.dp),

            horizontalArrangement = Arrangement.SpaceBetween
            // verticalAlignment = Alignment.CenterVertically


            // horizontalArrangement = Arrangement.SpaceAround
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    tint =
                    if(match.teamOne == player.team){
                       Color(0xFFb6defc)
                    }
                    else{
                        Color(0xFFfecbb8)
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .size(50.dp)

                )

                Text(
                    text = player.team,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(bottom =2.dp)
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.35f)
                //.padding(horizontal = 10.dp)
                //verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Text(
                        text = player.name,
                        style = MaterialTheme.typography.body1,
//                         overflow = TextOverflow.Ellipsis, maxLines = 1,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 15.dp, end = 2.dp)
                        // .background(Color.Red)

                    )

//                    Text(
//                        text = player.points,
//                        style = MaterialTheme.typography.body2,
//                        modifier = Modifier
//                            .weight(0.25f)
//                            // .background(Color.Yellow)
//                            .padding(start = 1.dp)
//                        , textAlign = TextAlign.Start
//                    )
//                    Text(
//                        text = player.salary,
//                        style = MaterialTheme.typography.body2,
//                        modifier = Modifier
//                            .weight(0.25f)
//                        // .background(Color.Magenta)
//                        //.padding(end = 4.dp),
//                        ,textAlign = TextAlign.Center
//                    )

                }
                Row (
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start

                ){


                    Text(
                        text = player.type,
                        style = MaterialTheme.typography.caption,
                        fontSize = 10.sp,
                        modifier= Modifier.padding(start=15.dp, bottom = 2.5.dp, end = 4.dp),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        Modifier
                           // .fillMaxWidth(0.5f)
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        //horizontalArrangement = Arrangement.Start
                    ) {
                        if(player.lastMatch == "1") {
                            RadioButton(
                                selected = true, onClick = {},
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(12.dp)
                                    .scale(0.5f)
                                    .padding(start = 4.dp)
                            )
                            Spacer(modifier = Modifier.padding(end = 4.dp))
                            Text(
                                text = "Played Last Match",
                                style = MaterialTheme.typography.caption,
                                fontSize = 8.sp,
                                modifier = Modifier
                                    .wrapContentHeight()
                                //.padding(start = 4.dp)

                            )
                        }
                    }

                }
                // Spacer(modifier = Modifier.padding(bottom = 4.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.45f),
               // verticalArrangement = Arrangement.Center
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
//                Surface(
//                    modifier = Modifier.size(50.dp),
//                    shape = CircleShape,
//                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
//                ) {
//                    // Image goes here
//                }
//
//                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                var checked by remember { mutableStateOf(player.isPlayerLocked) }
                var lockCvc by remember { mutableStateOf(player.isCvcLocked) }

                IconToggleButton(checked = lockCvc, onCheckedChange = {
                    player.lockCvc(it)
                    lockCvc = player.isCvcLocked
                    checked = player.isPlayerLocked
                }) {
                    val tint by animateColorAsState(if (player.isCvcLocked) Color(0xFFEC407A) else Color(0xFFB0BEC5))
                    Icon(Icons.Filled.Favorite, contentDescription = "Localized description", tint = tint)
                }

                IconToggleButton(checked = checked, onCheckedChange = {
                    player.lockPlayer(it)
                    checked = player.isPlayerLocked //if you update state variable, ui will update, even when it is not used in below block
                }) {
                    val tint by animateColorAsState(if (player.isPlayerLocked) Color(0xFFEC407A) else Color(0xFFB0BEC5))
                    Icon(Icons.Filled.Lock, contentDescription = "Localized description", tint = tint)
                }

                IconButton(onClick = { onPlayerRemoved(player) }) {
                    Icon(Icons.Outlined.Clear, contentDescription = "Localized description", tint = Color(0xFFB0BEC5))
                }
            }

//            Column(
//                modifier = Modifier
//                    .weight(0.08f)
//                    .fillMaxHeight(),
//                verticalArrangement = Arrangement.Center
//            ) {
//                //var checked by remember { mutableStateOf(false) }
//
//                IconButton(onClick = { onPlayerRemoved(player) }) {
//                    Icon(Icons.Outlined.Clear, contentDescription = "Localized description", tint = Color(0xFFB0BEC5))
//                }
//            }

        }
    }
}

@Composable
fun RemovedPlayerCard(
    player: Player,
    onPlayerAdded: (Player) -> Unit,
    match: Match
) {
    Column(
        modifier = Modifier
            //.padding(bottom = 5.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
    )
    {

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.3f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    tint =
                    if(match.teamOne == player.team){
                        Color(0xFFb6defc)
                    }
                    else{
                        Color(0xFFfecbb8)
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .size(50.dp)

                )

                Text(
                    text = player.team,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(bottom =2.dp)
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.35f)
                //.padding(horizontal = 10.dp)
                //verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Text(
                        text = player.name,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .weight(1f)
                           // .padding(start = 4.dp),
                         //.background(Color.Red)
                         ,textAlign =  TextAlign.Start
                    )
//
//                    Text(
//                        text = player.points,
//                        style = MaterialTheme.typography.body2,
//                        modifier = Modifier
//                            .weight(0.25f)
//                            // .background(Color.Yellow)
//                            .padding(start = 1.dp)
//                        , textAlign = TextAlign.Start
//                    )
//                    Text(
//                        text = player.salary,
//                        style = MaterialTheme.typography.body2,
//                        modifier = Modifier
//                            .weight(0.25f)
//                        // .background(Color.Magenta)
//                        //.padding(end = 4.dp),
//                        ,textAlign = TextAlign.Center
//                    )

                }
                Row (
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start

                ){


                    Text(
                        text = player.type,
                        style = MaterialTheme.typography.caption,
                        fontSize = 10.sp,
                        modifier= Modifier.padding(start=1.dp, bottom = 2.5.dp, end = 4.dp),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        Modifier
                            //.fillMaxWidth(0.5f)
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        //horizontalArrangement = Arrangement.Start
                    ) {
                        if(player.lastMatch == "1") {
                            RadioButton(
                                selected = true, onClick = {},
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(12.dp)
                                    .scale(0.5f)
                                    .padding(start = 4.dp)
                            )
                            Spacer(modifier = Modifier.padding(end = 4.dp))
                            Text(
                                text = "Played Last Match",
                                style = MaterialTheme.typography.caption,
                                fontSize = 8.sp,
                                modifier = Modifier
                                    .wrapContentHeight()
                                //.padding(start = 4.dp)

                            )
                        }
                    }

                }
                // Spacer(modifier = Modifier.padding(bottom = 4.dp))
            }



            Column(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //var checked by remember { mutableStateOf(false) }

                IconButton(onClick = { onPlayerAdded(player) }) {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "Localized description",
                        tint = Color.Green,
                        modifier = Modifier.padding(start = 2.dp)

                    )
                }
            }

        }
    }
}

@Composable
fun MyLazyList(
    playersList: List<Player>,
    onPlayerRemoved: (Player) -> Unit,
    match: Match
    //lockPlayer:(player:Player, truth:Boolean)->Unit,
    //lockCvc:(player:Player, truth:Boolean)->Unit
){
    //val myList = Player().Random11(playersList)
    //val myList = Player().loadPlayers()
    Column {
        Row(Modifier.background(Color(0xFFFAFAFA))) {
            Spacer(modifier =Modifier.weight(0.2f))
            Text(
                text = "Player",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .weight(0.35f)
                    .padding(start = 14.dp, bottom = 8.dp, top = 4.dp)//.background(Color.Red)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Start
            )
            Text(
                text = "  C/Vc",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .weight(0.15f)
                    .padding(bottom = 8.dp, top = 4.dp) //.background(Color.Yellow)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Lock Player",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(bottom = 8.dp, top = 4.dp) //.background(Color.Magenta)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Start
            )
//            Spacer(modifier =Modifier.weight(0.35f))

        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        LazyColumn( modifier = Modifier.fillMaxWidth(),
            //contentPadding = PaddingValues(5.dp)
        ){
            // item { Text(text = "One", Modifier.padding(bottom = 10.dp)) }


            items( playersList ){
                    player->
                PlayerCard(player, onPlayerRemoved, match )
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }





}

@Composable
fun RemovePlayersLazyList(
    playersList: List<Player>,
    onPlayerAdded: (Player) -> Unit,
    match: Match
){
    //val myList = Player().Random11(playersList)
    //val myList = Player().loadPlayers()
    Column {
        Row(Modifier.background(Color(0xFFFAFAFA)).padding(horizontal = 4.dp)) {
            Spacer(modifier =Modifier.weight(0.32f))
            Text(
                text = "Player",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .weight(0.4f)
                    .padding( bottom = 8.dp, top = 4.dp) //.background(Color.Red)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Start
            )
//            Text(
//                text = "Points",
//                style = MaterialTheme.typography.caption,
//                modifier = Modifier
//                    .weight(0.25f)
//                    .padding(bottom = 8.dp, top = 4.dp) //.background(Color.Yellow)
//                    .align(Alignment.CenterVertically),
//                textAlign = TextAlign.Start
//            )
            Text(
                text = "Add ",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .weight(0.2f)
                    .padding(bottom = 8.dp, top = 4.dp) ///.background(Color.Magenta)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
            //Spacer(modifier =Modifier.weight(0.35f))

        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        LazyColumn( modifier = Modifier.fillMaxWidth(),
            //contentPadding = PaddingValues(5.dp)
        ){
            // item { Text(text = "One", Modifier.padding(bottom = 10.dp)) }


            items( playersList ){
                    player->
                RemovedPlayerCard(player, onPlayerAdded,match)
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }





}

@Composable
fun LazyListWithNav(
    navController: NavController,
    players: List<Player>,
    onPlayerRemoved: (Player) -> Unit,
    generateTeams:(List<Player>, noOfTeams:Int)->Unit,
    validateSelectedPlayers:()->Int,
    noOfTeams: Int,
    match: Match

){
    val openDialog = rememberSaveable{ mutableStateOf(1) }
//    val localFocusManager = LocalFocusManager.current



    Column(
        // horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
//            .pointerInput(Unit) {
//                detectTapGestures(onTap = {
//                    localFocusManager.clearFocus()
//                })
//            }
        //verticalArrangement = Arrangement.SpaceBetween

    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ) {
            MyLazyList(players, onPlayerRemoved ,match)
        }

        var numberValue by remember { mutableStateOf(noOfTeams)}
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                //.background(color = Color.White)
                //.padding(bottom = 4.dp)
            ,verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(
                onClick = {
                    openDialog.value = validateSelectedPlayers()
                    if( openDialog.value== 1){
                        generateTeams(players.toList(), numberValue)
                        navController.navigate(Screen.GeneratedTeams.route)
                    }
                },
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight().border(1.dp, Color.White)
                //.align(Alignment.CenterHorizontally),

            ) {
                Text(text = "Generate Teams",
                    Modifier
//                    .weight(0.4f)
                        // .padding(4.dp)
                        .align(Alignment.CenterVertically))

            }
            Row( verticalAlignment = Alignment.CenterVertically,
              //  horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight()
                    .background(Color(0xFF6e7ef6)).border(1.dp, Color.White)
            ){
                IconButton(onClick = {
                    numberValue = if ((numberValue - 1) > 1) numberValue-1 else 1
                } ,  modifier = Modifier.weight(0.25f)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        tint = Color.White,
                        contentDescription = null,
                    )
                }

                Text(
                    "$numberValue",
                    color = Color.White,
                    fontWeight = FontWeight.Bold ,modifier = Modifier.weight(0.5f)
                   , textAlign = TextAlign.Center
                )

                IconButton(onClick = { numberValue = if ((numberValue + 1) <1000 ) numberValue+1 else 1000 },
                    modifier = Modifier.weight(0.25f)) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(0.2f).border(1.dp, Color.White)


            ) {
                Button(
                    onClick = { numberValue = if ((numberValue + 50) <1000 ) numberValue+50 else 1000 },
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth(), contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "+ 50",
                        style =  MaterialTheme.typography.caption,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp
                    )
                }
              Spacer(modifier = Modifier.padding(1.dp))
                Button(
                    onClick = { numberValue = if ((numberValue - 50) > 1 ) numberValue-50 else 1 },
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth(), contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "- 50", style = MaterialTheme.typography.caption,fontSize = 12.sp, fontWeight = FontWeight.ExtraBold)
                }
            }

        }
        if (openDialog.value != 1) {
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    openDialog.value = 1
                },
                title = {
                    Text(text = "Its good to follow few rules")
                },
                text = {
                    Text(
                        "Select minimum of 1 WK, 3 BAT, 1 ALL, 3 BOWL and 4 players from each team." +
                                "Do not lock more than 11 players."
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = 1
                        }
                    ) {
                        Text("Confirm")
                    }
                }
            )
        }
    }

}


@Composable
fun PlayersWithTabs(navController: NavController,
                    players: List<Player>,
                    onPlayerRemoved: (Player) -> Unit,
                    generateTeams:(List<Player>, noOfTeams:Int)->Unit,
                    onPlayerAdded:(Player)->Unit,
                    match: Match,
                    removedPlayers:List<Player>,
                    validateSelectedPlayers:()->Int,
                    noOfTeams: Int
){
    var selectedIndex by remember { mutableStateOf(0)}
    Column(Modifier.fillMaxSize()) {
        MatchCard(match = match)
        Divider(thickness = 1.dp, color = Color.LightGray)
        TabRow(selectedTabIndex = selectedIndex, backgroundColor = Color.White) {
            Tab(
                selected = true,
                onClick = { selectedIndex = 0 },
                text = { Text(text = "All Players") }
            )
            Tab(selected = true, onClick = { selectedIndex = 1 }, text = {Text(text = "Removed Players")})
        }
        if(selectedIndex==0){
            LazyListWithNav(navController = navController, players = players, onPlayerRemoved =onPlayerRemoved
                , generateTeams = generateTeams, validateSelectedPlayers, noOfTeams , match)
        }
        else {
            RemovePlayersLazyList(playersList = removedPlayers, onPlayerAdded = onPlayerAdded,match)
        }
    }

}