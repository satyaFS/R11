package com.randys11.r11.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.randys11.r11.model.Match


@Composable
fun MatchCard(match:Match, navController: NavController, loadPlayers:(Match)->Unit){
    Card(modifier = Modifier
        .padding(bottom = 15.dp)
        .fillMaxWidth()
        .height(80.dp)
        .clickable {
            loadPlayers(match)
            navController.navigate(Screen.Players.route)
        }
        , elevation = 5.dp

    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            //.background(MaterialTheme.colors.surface),
            , Arrangement.Center) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.20f)
                    .background(Color(0xFFFAFAFA)),
                Arrangement.Center,
                Alignment.CenterVertically

            )
            {
                Text(
                    text = match.league,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 1.dp)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                , Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Text(
                    text = match.teamOne,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Vs",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth(0.33f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = match.teamTwo,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }

        }
    }
}

@Composable
fun MatchCard(match: Match){
    Card(modifier = Modifier
        //.padding(bottom = 15.dp)
        .fillMaxWidth()
        .height(80.dp)
        , elevation = 5.dp

    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            //.background(MaterialTheme.colors.surface),
            , Arrangement.Center) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.20f)
                    .background(Color(0xFFFAFAFA)),
                Arrangement.Center,
                Alignment.CenterVertically

            )
            {
                Text(
                    text = match.league,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 1.dp)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                , Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Text(
                    text = match.teamOne,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Vs",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth(0.33f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = match.teamTwo,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }

        }
    }
}

@Composable
fun LazyMatches(matches:List<Match>, navController: NavController, loadPlayers: (Match) -> Unit){
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        ,contentPadding = PaddingValues(6.dp)
    ){
        items(matches){match->
            MatchCard(match, navController, loadPlayers )
        }

    }
}
