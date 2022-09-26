package algo2

//Algo Name: Words in letter array
//Inspired from the youtube video: https://www.youtube.com/watch?v=abkHxIMJGIw

/**
The Author states in the end of the vid about a challenge:
        Which is to prevent looping back to the same element you came from

*/

const val rectangleRaw = """
    KOTE
    NULE
    AFIN
"""

const val dictionaryRaw = "Kotlin, fun, file, line, null"
val directions = listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

val rectangle = LetterRectangleAlgo().convertStringToListOfStrings(rectangleRaw,"\n")
val dictionary = dictionaryRaw.toUpperCase().split(", ").toSet()
val isWordUsed = mutableListOf<Int>()


fun constructList(): MutableList<Int>{
    for (i in dictionary){
        isWordUsed.add(0)
    }
    return isWordUsed
}

fun main(){
    constructList()

    val prefixes =  dictionary.flatMap { word ->
        List(word.length +1){word.take(it)}
    }.toSet()

    for (y in rectangle.indices) for (x in rectangle[0].indices){
        LetterRectangleAlgo().search(x= x,y= y, directions =  directions, word = rectangle[y][x].toString(), actualLettersToBeFetched = rectangle, comparedItems =  dictionary, prefixes = prefixes,1 to 0)
    }

}
class LetterRectangleAlgo {

    fun convertStringToListOfStrings(value:String, delimiter:String) : List<String>{
        return value.trimIndent().split(delimiter)
    }

    private fun convertDirectionToOpposite(position: Pair<Int,Int>):Pair<Int,Int>{
        val toBeMultiplied =  if (position.first != 0  ) {
            position.first * -1 to 0
        } else {
            0 to position.second * -1
        }
        return  toBeMultiplied
    }

    fun search(x:Int,y:Int,directions : List<Pair<Int,Int>>,word: String, actualLettersToBeFetched: List<String>, comparedItems: Set<String>, prefixes: Set<String>, position: Pair<Int,Int> = 0 to 0 ){

         if (word !in prefixes ) return

        if (checkIfWordComplete(word,comparedItems= comparedItems)) return


            for ((dx, dy) in directions) {
                if (dx to dy != convertDirectionToOpposite(position)) {

                val xNew = x + dx
                val yNew = y + dy

                val letter =
                    actualLettersToBeFetched.getOrNull(yNew)?.getOrNull(xNew) ?: continue // If it goes out of bounds
                    if (checkIfWordComplete(word, letter, comparedItems)) return
                    search(xNew, yNew, directions, word + letter, actualLettersToBeFetched, comparedItems, prefixes, dx to dy)
            }

        }
    }
    private fun setWordUsed(word: String){
        dictionary.forEach {
            if (it == word) isWordUsed[dictionary.indexOf(it)] = 1
        }
    }

    private fun isWordUsed(word: String) : Boolean{
        var check = false
        dictionary.forEach {
            if (it == word) check = isWordUsed[dictionary.indexOf(it)] == 1
        }
        return check
    }

    private fun checkIfWordComplete(
        word: String,
        letter: Char? = null,
        comparedItems: Set<String>
    ): Boolean {
        val checkedWord = if (letter != null){
            word + letter
        }   else {
            word
        }
        if (checkedWord in comparedItems && !isWordUsed(checkedWord)) {
            println(checkedWord)
            setWordUsed(checkedWord)
            return true
        }
        return false
    }


}