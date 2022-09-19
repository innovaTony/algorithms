package algo1


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
