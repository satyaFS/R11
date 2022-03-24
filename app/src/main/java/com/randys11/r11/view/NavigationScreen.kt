package com.randys11.r11.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.randys11.r11.PlayerViewModel
@Composable
fun Navigation(viewModel: PlayerViewModel, navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.ListOfMatches.route){
        composable(route = Screen.Players.route){
            if(viewModel.initiaLloading.value){
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
            else  {
//                LazyListWithNav(
//                    navController = navController,
//                    viewModel.selectedPlayers,
//                    viewModel::removePlayer,
//                    viewModel::generateTeams,
//                    // viewModel::LockPlayer,
//                    // viewModel::LockCvc
//                )
                PlayersWithTabs(
                    navController = navController,
                    players = viewModel.selectedPlayers,
                    onPlayerRemoved = viewModel::removePlayer,
                    generateTeams = viewModel::generateTeams,
                    onPlayerAdded = viewModel::addPlayer,
                    match = viewModel.selectedMatch,
                    removedPlayers = viewModel.removedPlayers,
                    validateSelectedPlayers = viewModel::validateSelectedPlayers,
                    noOfTeams = viewModel.noOfTeams
                )
            }


        }

        composable(
            route = Screen.GeneratedTeams.route,
        ){
            if(viewModel.loading.value){
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
            else{
                LazyTeams(viewModel.generatedTeams, viewModel.selectedMatch)
            }

        }

        composable(route = Screen.ListOfMatches.route, ){
            if(viewModel.loading.value){
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
            else
                LazyMatches(matches = viewModel.list, navController, viewModel::loadPlayers)

        }
    }
}


//App bar
@Composable
fun ToolbarWidget(viewModel: PlayerViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // theme for our app.
    Scaffold(
        // below line we are
        // creating a top bar.
        topBar = {
            TopAppBar(
                // in below line we are
                // adding title to our top bar.
                title = {
                    // inside title we are
                    // adding text to our toolbar.
                    if(navBackStackEntry?.destination?.route==Screen.ListOfMatches.route)
                        Text(
                            text = "Select a Match",
                            // below line is use
                            // to give text color.
                            Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    else if(navBackStackEntry?.destination?.route==Screen.Players.route){
                        Text(
                            text = "Make Your Team",
                            // below line is use
                            // to give text color.
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }else {
                        Text(
                            text = "GL Teams",
                            // below line is use
                            // to give text color.
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                },
//                navigationIcon = {
//                    // navigation icon is use
//                    // for drawer icon.
//                    IconButton(onClick = { }) {
//                        // below line is use to
//                        // specify navigation icon.
//                        Icon(Icons.Rounded.Star, contentDescription =null )
//                    }
//                },
                // below line is use to give background color
               // backgroundColor = colorResource(id = R.color.purple_200),

                // content color is use to give
                // color to our content in our toolbar.
                contentColor = Color.White,

                // below line is use to give
                // elevation to our toolbar.
                elevation = 12.dp
            )
        }, content = {
            Navigation(viewModel =viewModel, navController)
        }
    )
}


