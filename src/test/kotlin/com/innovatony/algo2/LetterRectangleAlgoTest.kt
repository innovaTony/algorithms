package com.innovatony.algo2

import com.innovatony.algo2.LetterRectangleAlgo
import com.innovatony.algo2.LettersAlgo.isWordUsed
import com.innovatony.algo2.LettersAlgo.presentWords
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

class LetterRectangleAlgoTest {

    @BeforeTest
    fun `Empty Rivers Before Each test so results won't conflict`() {
        isWordUsed.clear()
        presentWords.clear()
    }

    @Test
    fun `Check if no word at all in 2D dimension`() {
        val rectangleRaw = """
    K1TE
    N1LE
    AF1N
"""
        val dictionaryRaw = "Kotlin, fun, file, line, null"
        val directions = listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)
        LetterRectangleAlgo().runLettersInDictionaryAlgo(rectangleRaw, dictionaryRaw, directions)

        assert(presentWords.size == 0)

    }

    @Test
    fun `Check 2 letters in a dictionary word are in 2D array adjacent diagonally`() {
        val rectangleRaw = """
    KOT0
    00LN
    00I0
"""
        val dictionaryRaw = "Kotlin"
        val directions = listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)
        LetterRectangleAlgo().runLettersInDictionaryAlgo(rectangleRaw, dictionaryRaw, directions)

        assert(presentWords.size == 0)
    }

    //Optional
    @Test
    fun `Check if decided to add diagonal direction the algorithm still works`() {
        val rectangleRaw = """
    KOT0
    00LN
    00I0
"""

        val dictionaryRaw = "Kotlin"
        val directions = listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1, -1 to -1, 1 to 1, -1 to 1, 1 to -1)
        LetterRectangleAlgo().runLettersInDictionaryAlgo(rectangleRaw, dictionaryRaw, directions)

        assert(presentWords.size == 1)
    }

    @Test
    fun `Check if a duplicate word would be returned`() {
        val rectangleRaw = """
    KOT0
    00LN
    0NIN
"""
        val dictionaryRaw = "Kotlin, fun, file, line, null"
        val directions = listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)
        LetterRectangleAlgo().runLettersInDictionaryAlgo(rectangleRaw, dictionaryRaw, directions)

        assert(presentWords.size == 1)
    }

    @Test
    fun `Check if 4 or multiple dictionary words are returned when present in 2D array `() {
        val rectangleRaw = """
    KOTE
    NULE
    AFIN
"""

        val dictionaryRaw = "Kotlin, fun, file, line, null"
        val directions = listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)


        LetterRectangleAlgo().runLettersInDictionaryAlgo(rectangleRaw, dictionaryRaw, directions)

        assert(presentWords.size == 4)

    }

    @Test
    fun `Check if non adjacent letter would return any word  `() {
        val rectangleRaw = """
    KOT0
    00L0
    N0I0
"""

        val dictionaryRaw = "Kotlin"
        val directions = listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)


        LetterRectangleAlgo().runLettersInDictionaryAlgo(rectangleRaw, dictionaryRaw, directions)

        assert(presentWords.size == 0)

    }


}