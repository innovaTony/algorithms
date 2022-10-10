package algo2

//Algo Name: Words in letter array
//Inspired from the youtube video: https://www.youtube.com/watch?v=abkHxIMJGIw

/**
The Author states in the end of the vid about a challenge:
        Which is to prevent looping back to the same element you came from

*/
import algo2.LettersAlgo.isWordUsed
import algo2.LettersAlgo.presentWords

object LettersAlgo{
    val isWordUsed = mutableListOf<Int>()
    val presentWords = mutableListOf<String>()
}

fun main(){
     val rectangleRaw = """
    KOTE
    NULE
    AFIN
"""

    val dictionaryRaw = "Kotlin, fun, file, line, null"
    val directions = listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)


    LetterRectangleAlgo().runLettersInDictionaryAlgo(rectangleRaw, dictionaryRaw, directions)

}

class LetterRectangleAlgo {

    fun runLettersInDictionaryAlgo(
        rectangleRaw: String,
        dictionaryRaw: String,
        directions: List<Pair<Int, Int>>
    ) {
        val rectangle = LetterRectangleAlgo().convertStringToListOfStrings(rectangleRaw, "\n")
        val dictionary = dictionaryRaw.toUpperCase().split(", ").toSet()


        LetterRectangleAlgo().constructList(dictionary)

        //Join existing words from the given to a set
        val prefixes = dictionary.flatMap { word ->
            List(word.length + 1) { word.take(it) }
        }.toSet()

        for (y in rectangle.indices) for (x in rectangle[0].indices) {
            LetterRectangleAlgo().search(
                x = x,
                y = y,
                directions = directions,
                word = rectangle[y][x].toString(),
                actualLettersToBeFetched = rectangle,
                comparedItems = dictionary,
                prefixes = prefixes,
                1 to 0
            )
        }
    }








    private fun convertStringToListOfStrings(value:String, delimiter:String) : List<String>{
        return value.trimIndent().split(delimiter)
    }

     private fun constructList(dictionary:Set<String>): MutableList<Int>{ //Form a list dynamically, and set the initial value of each index to 0, which will be reassigned to 1 for used words
        for (i in dictionary){
            isWordUsed.add(0)
        }
        return isWordUsed
    }

    private fun convertDirectionToOpposite(position: Pair<Int,Int>):Pair<Int,Int>{
        //Pass the value of the position this is coming from, so we make sure to not get back to same letter
        val toBeMultiplied =  if (position.first != 0  ) {
            position.first * -1 to 0
        } else {
            0 to position.second * -1
        }
        return  toBeMultiplied
    }

   private fun search(x:Int,y:Int,directions : List<Pair<Int,Int>>,word: String, actualLettersToBeFetched: List<String>, comparedItems: Set<String>, prefixes: Set<String>, position: Pair<Int,Int> = 0 to 0 ){

         if (word !in prefixes ) return

        if (checkIfWordComplete(word,comparedItems= comparedItems)) return


            for ((dx, dy) in directions) { //Loop 90 degrees
                if (dx to dy != convertDirectionToOpposite(position)) { //Prevent going to the position this is coming from

                val xNew = x + dx
                val yNew = y + dy

                val letter =
                    actualLettersToBeFetched.getOrNull(yNew)?.getOrNull(xNew) ?: continue // If it goes out of bounds
                    if (checkIfWordComplete(word, letter, comparedItems)) return
                    search(xNew, yNew, directions, word + letter, actualLettersToBeFetched, comparedItems, prefixes, dx to dy)
            }

        }
    }
    private fun setWordUsed(word: String,dictionary:Set<String>){ //Iterate through the list and assign the 1 value for used words, keep 0 otherwise
        dictionary.forEach {
            if (it == word) isWordUsed[dictionary.indexOf(it)] = 1
        }
    }

    private fun isWordUsed(word: String,dictionary:Set<String>) : Boolean{ //Check if word is used
        var check = false
        dictionary.forEach {
            if (it == word) check = isWordUsed[dictionary.indexOf(it)] == 1
        }
        return check
    }

    private fun checkIfWordComplete( //Here we can check if the letters added form a complete word
        word: String,
        letter: Char? = null,
        comparedItems: Set<String>
    ): Boolean {
        val checkedWord = if (letter != null){
            word + letter
        }   else {
            word
        }
        if (checkedWord in comparedItems && !isWordUsed(checkedWord,comparedItems)) { //Both checks shall conform: word present in current given dictionary, and it shall not be used, so it won't fire several times
            println(checkedWord)
            presentWords.add(checkedWord)
            setWordUsed(checkedWord,comparedItems)
            return true
        }
        return false
    }


}