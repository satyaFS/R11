package com.randys11.r11.model

data class Player constructor(
    private var _name: String? = "",
    private var _team: String? = "",
    private var _salary: String? = "",
    private var _points: String? = "",
    private var _lastMatch: Boolean? = false,
    private var _type: String? = "",
    private var _isPlayerLocked: Boolean? = false,
    private var _isCvcLocked: Boolean? = false,
    private var _isCaptain:Boolean? = false,
    private var _isViceCaptain:Boolean? = false
) {
    val name get() = _name!!
    val team get() = _team!!
    val salary get() = _salary!!
    val points get() = _points!!
    val lastMatch get() = _lastMatch!!
    val type get() = _type!!
    val isPlayerLocked get() = _isPlayerLocked!!
    val isCvcLocked get() = _isCvcLocked!!
    val isCaptain get() = _isCaptain!!
    val isViceCaptain get() = _isViceCaptain!!



    private val possiblePlayer: List<PlayersCombination> = listOf<PlayersCombination>(
        PlayersCombination(1, 3, 3, 4),
        PlayersCombination(1, 3, 2, 5),
        PlayersCombination(1, 4, 3, 3),
        PlayersCombination(1, 4, 2, 4),
        PlayersCombination(1, 4, 1, 5),
        PlayersCombination(1, 5, 2, 3),
        PlayersCombination(1, 5, 1, 4),
        PlayersCombination(1, 3, 4, 3),
        PlayersCombination(1, 6, 1, 3),
        PlayersCombination(2, 3, 1, 5),
        PlayersCombination(2, 3, 2, 4),
        PlayersCombination(2, 4, 1, 4),
        PlayersCombination(2, 4, 2, 3),
        PlayersCombination(2, 5, 1, 3),
        PlayersCombination(3, 3, 1, 4),
        PlayersCombination(3, 3, 2, 3),
        PlayersCombination(3, 4, 1, 3),
        PlayersCombination(4, 3, 1, 3),
    )

   data class PlayersCombination(val wk: Int, val bat: Int, val ar: Int, val bow: Int)

    fun random11(list: List<Player>, noOfTeams:Int): List<List<Player>> {
        //max number of players possible for each player type
        val wkList = list.filter { it.type == "WK" }
        val batList = list.filter { it.type == "BAT" }
        val bowList = list.filter { it.type == "BOWL" }
        val arList = list.filter { it.type == "ALL" }

        //print(bowList)
        //number of players fixed in each player type
        val wkListFixed = wkList.filter { it.isPlayerLocked }.count()
        val batListFixed = batList.filter { it.isPlayerLocked }.count()
        val bowListFixed = bowList.filter { it.isPlayerLocked }.count()
        val arListFixed = arList.filter { it.isPlayerLocked }.count()
        print(bowListFixed)


        val finalTeam = mutableListOf<Player>()


        var teamCombinationList = possiblePlayer.filter {
            (it.wk <= wkList.count() && it.wk >= wkListFixed) && (it.bat <= batList.count() && it.bat >= batListFixed)
                    && (it.bow <= bowList.count() && it.bow >= bowListFixed) && (it.ar <= arList.count() && it.ar >= arListFixed)
        }


        var duplicateTeams = 0
        var teamCount = 0

        var totalTeams: MutableList<List<Player>> = mutableListOf<List<Player>>()
        while (totalTeams.count() < noOfTeams && duplicateTeams < 30) {
            val selectedCombination = teamCombinationList.random()
            print(selectedCombination)

            finalTeam.addAll(list.filter { it.isPlayerLocked })
            finalTeam.addAll(
                wkList.filter { !it.isPlayerLocked }.asSequence().shuffled()
                    .take(selectedCombination.wk - wkListFixed)
            )
            finalTeam.addAll(
                batList.filter { !it.isPlayerLocked }.asSequence().shuffled()
                    .take(selectedCombination.bat - batListFixed)
            )
            finalTeam.addAll(
                bowList.filter { !it.isPlayerLocked }.asSequence().shuffled()
                    .take(selectedCombination.bow - bowListFixed)
            )
            finalTeam.addAll(
                arList.filter { !it.isPlayerLocked }.asSequence().shuffled()
                    .take(selectedCombination.ar - arListFixed)
            )
            teamCount = finalTeam.filter { it.team == batList[0].team }.count()

            var finalTeamDeepCopy = finalTeam.map { it.copy() }

            //Select a captain
            val lockedCap = finalTeamDeepCopy.filter { it.isCvcLocked }.randomOrNull()
            if (lockedCap != null) lockedCap._isCaptain = true
            else{
                val cap = finalTeamDeepCopy.randomOrNull()
                if(cap != null) cap._isCaptain = true
            }
            //Select a vice Cap
            val lockedViceCap = finalTeamDeepCopy.filter { it.isCvcLocked && !it.isCaptain }.randomOrNull()
            if (lockedViceCap != null) lockedViceCap._isViceCaptain = true
            else{
                val cap = finalTeamDeepCopy.filter { !it.isCaptain }.randomOrNull()
                if(cap != null) cap._isViceCaptain = true
            }

            //print(finalTeam.filter { it.type == "BOWL" })
            //println(totalTeams.count())
            // println(finalTeam.sumOf { it.salary.toDouble() })

            if ((teamCount in 4..7) &&
                (finalTeamDeepCopy.sumOf { (it.salary).toDouble() } <= 100) && !totalTeams.contains(
                    finalTeamDeepCopy
                )
            ) {
                totalTeams.add(finalTeamDeepCopy.toList())//
                duplicateTeams = 0
            }
            finalTeam.clear()
            duplicateTeams++
            println("duplicate--> $duplicateTeams")
            //println(totalTeams)

            // println("$teamCount" + " another round" )
        }


//        println("${selectedCombination.ar}" + "ALL")
//        println(selectedCombination.bat)
//        println(selectedCombination.bow)
//        println(selectedCombination.wk)


        return totalTeams;
    }

    fun removeState(){
        this._isCvcLocked = false
        this._isPlayerLocked = false
    }

    fun lockPlayer(truth: Boolean) {
        this._isPlayerLocked = truth
    }

    fun lockCvc(truth: Boolean) {
        this._isCvcLocked = truth
    }


}
