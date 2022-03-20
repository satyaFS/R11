package com.randys11.r11.view

sealed class Screen(val route:String){
    object Players :Screen("allPlayers")
    object GeneratedTeams:Screen("GeneratedTeams")
    object ListOfMatches:Screen("listOfMatches")

}
