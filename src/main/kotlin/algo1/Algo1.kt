package algo1

import algo1.RiversAlgoVariables.riverId
import algo1.RiversAlgoVariables.rivers
import algo1.RiversAlgoVariables.seatId
import algo1.RiversAlgoVariables.twoDimensionalArray

//Algo Name: River sizes
//Inspired from this video:    https://www.youtube.com/watch?v=r4TgqWbKRtA

//You need two or more adjacent 1s in the 2D array to form a river, they need to be positioned horizontally or vertically adjacent (Not diagonal)

object RiversAlgoVariables{
    var seatId = 0
    var riverId = 0
    val rivers = mutableListOf<Seat>()

    val twoDimensionalArray = arrayOf(
        arrayOf(1, 0, 1),
        arrayOf(0, 1, 1),
        arrayOf(1, 1, 0),
        arrayOf(0, 1, 1)
    )
}


fun main(){

    runRiversAlgo()
}

 fun runRiversAlgo() {
    //Provide the function that will detect Seats with the corresponding nested for loop
    detectSeats()

    //Start the fetching operation below
    fetchRivers()

    //Below is the list of seats with their corresponding river they are contained in or -1 if they are not part of any river
    println("So the Result is: $rivers ")

    //The operation below is to organize the presentation of the rivers as asked for by the algorithms authors
    rivers.apply {
        val result = mutableMap()

        //The below will display size of each river as value and the Key will differentiate each river
        println("Number of seats per river: $result")
        println("Number of seats per river: ${result.values}")

        //Provide the array the algorithm's authors asked for
        val resultArray = ints(result)
        println("Number of seats per river(Resulting Array): $resultArray")
    }
}

private fun MutableList<Seat>.mutableMap(): MutableMap<Int, Int> {
    val result = mutableMapOf<Int, Int>()
    this.forEach {
        if (it.riverId != -1) {
            if (result[it.riverId] == null) {
                result[it.riverId] = 1
            } else {
                val oldValue = result[it.riverId]
                result[it.riverId] = oldValue!! + 1
            }
        }
    }
    return result
}

private fun ints(result: MutableMap<Int, Int>): ArrayList<Int> {
    val resultArray = arrayListOf<Int>()
    result.values.forEach {
        resultArray.add(it)
    }
    return resultArray
}

private fun fetchRivers() {
    rivers.apply {
        for (i in this) {
            checkFourDirections(i.x, i.y)
            riverId++ //When the above operation completes, increment the riverId
        }
    }
}

private fun detectSeats() {
    for (y in twoDimensionalArray[0].indices) for (x in twoDimensionalArray.indices) {
        getSeats(x = x, y = y)
    }
}


data class Seat  ( //Each element equal to 1 inside the 2D array will be considered as a Seat for 1 and hence the naming
    var id: Int,
    var x : Int,
    var y : Int,
    var riverId : Int = -1
){
    init {
        seatId++
    }
}


private fun getSeats(x:Int,y:Int) { //Function that extracts all seats from 2D array

    twoDimensionalArray.getOrNull(x)?.getOrNull(y) ?: return //If current element out of position then return from function
    if (twoDimensionalArray[x][y] != 1) {
        return
    }  //If current element not equal to 1 then return from function

    rivers.add( Seat(seatId, x, y))

}


private fun checkFourDirections(x:Int,y:Int){
    if (!checkIfSeatExists(x,y))return
    var a = 0
    var b = 0
    var i = 0
    while (i < 4) {

        when ( i) {
            0 -> { //Checking to the up direction
                a = x
                b = y - 1
                operationSetAndCheckForAllDirections(a, b, x, y)
            }

            1 -> { //Checking to the left direction
                a = x - 1
                b = y
                operationSetAndCheckForAllDirections(a, b, x, y)
            }

            2 -> { //Checking to the right direction
                a = x + 1
                b = y
                operationSetAndCheckForAllDirections(a, b, x, y)
            }

            3 -> { //Checking to the down direction
                a = x
                b = y + 1
                operationSetAndCheckForAllDirections(a, b, x, y)
            }
        }

        i++
    }
}

private fun operationSetAndCheckForAllDirections(
    a: Int,
    b: Int,
    x: Int,
    y: Int
) {
    if (checkIfSeatExists(a, b) && !checkIfSeatAlreadyProcessed(a,b)) {

        setSeatToRiver(x, y, a, b)
        checkFourDirections(a, b)
    }
}
private fun setSeatToRiver(x: Int, y: Int, a: Int, b: Int) {
    //Assign each seat with its corresponding riverId
    rivers.forEach {
        if(it.x == x && it.y == y && it.riverId== -1 )it.riverId = riverId
    }
    rivers.forEach {
        if(it.x == a && it.y == b && it.riverId== -1)it.riverId = riverId
    }
}


private fun checkIfSeatExists(x:Int,y:Int):Boolean{ //If x & y values are assigned to any seat in the river list, then this seat exists
    var existsCheck = false
    rivers.forEach {
        if ( it.x == x && it.y == y) existsCheck = true
    }
    return existsCheck
}

private fun checkIfSeatAlreadyProcessed(x:Int,y:Int):Boolean{
    var processedCheck = false
    rivers.forEach { //If riverId different than -1, then a riverId was already assigned to this seat and hence making it processed
        if ( it.x == x && it.y == y && it.riverId != -1) processedCheck = true
    }
    return processedCheck
}
