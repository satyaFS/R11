package com.randys11.r11.model

data class Match(
    var _league: String?="",
    var _teamOne: String?="",
    var _teamTwo:String?="",
    var _teamOneSC:String?="",
    var _teamTwoSC:String?="",
    var _startTime:String?= "",
    var _matchID:String?= ""
){
    val league get() = _league!!
    val teamOne get() = _teamOne!!
    val teamTwo get() = _teamTwo!!
    val teamOneSC get() = _teamOneSC!!
    val startTime get() = _startTime!!
    val teamTwoSC get( ) = _teamTwoSC!!
    val matchID get() = _matchID!!
}
