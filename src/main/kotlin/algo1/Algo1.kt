package algo1


var seatId = 0

val twoDimensionalArray = arrayOf(
    arrayOf(1, 0, 1),
    arrayOf(0, 1, 1),
    arrayOf(1, 1, 0),
    arrayOf(0, 1, 1)
)
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