package algo1

//Rivers Algo
//Inspired from this video:    https://www.youtube.com/watch?v=r4TgqWbKRtA

var seatId = 0
var riverId = 0
val rivers = mutableListOf<Seat>()

val twoDimensionalArray = arrayOf(
    arrayOf(1, 0, 1),
    arrayOf(0, 1, 1),
    arrayOf(1, 1, 0),
    arrayOf(0, 1, 1)
)
fun main(){


    for (y in twoDimensionalArray[0].indices) for (x in twoDimensionalArray.indices) {
        getSeats(x= x,y= y)
    }

    rivers.apply {
        for (i in this){
            checkFourDirections(i.x,i.y)
            riverId++ //When the above operation completes, increment the riverId
        }
    }

    println("So the Result is: $rivers ")

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


fun getSeats(x:Int,y:Int) { //Function that extracts all seats from 2D array

    twoDimensionalArray.getOrNull(x)?.getOrNull(y) ?: return //If current element out of position then return from function
    if (twoDimensionalArray[x][y] != 1) {
        return
    }  //If current element not equal to 1 then return from function

    rivers.add( Seat(seatId, x, y))

}


fun checkFourDirections(x:Int,y:Int){
    if (!checkIfSeatExists(x,y))return
    var a = 0
    var b = 0
    var i = 0
    while (i < 4) {

        when ( i) {
            0 -> {
                a = x
                b = y - 1
                operationSetAndCheckForAllDirections(a, b, x, y)
            }

            1 -> {
                a = x - 1
                b = y
                operationSetAndCheckForAllDirections(a, b, x, y)
            }

            2 -> {
                a = x + 1
                b = y
                operationSetAndCheckForAllDirections(a, b, x, y)
            }

            3 -> {
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


fun checkIfSeatExists(x:Int,y:Int):Boolean{ //If x & y values are assigned to any seat in the river list, then this seat exists
    var existsCheck = false
    rivers.forEach {
        if ( it.x == x && it.y == y) existsCheck = true
    }
    return existsCheck
}

fun checkIfSeatAlreadyProcessed(x:Int,y:Int):Boolean{
    var processedCheck = false
    rivers.forEach { //If riverId different than -1, then a riverId was already assigned to this seat and hence making it processed
        if ( it.x == x && it.y == y && it.riverId != -1) processedCheck = true
    }
    return processedCheck
}
