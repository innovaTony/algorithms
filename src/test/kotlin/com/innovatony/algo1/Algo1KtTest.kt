package com.innovatony.algo1

import com.innovatony.algo1.RiversAlgoVariables.rivers
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

class Algo1KtTest {

    @BeforeTest
    fun `Empty Rivers Before Each test so results won't conflict`() {
        rivers.clear()
    }

    @Test
    fun `Check when array has no ones at all`() {
        val twoDimensionalArray = arrayOf(
            arrayOf(0, 0, 0),
            arrayOf(0, 0, 0),
            arrayOf(0, 0, 0),
            arrayOf(0, 0, 0)
        )
        val resultArray = runRiversAlgo(twoDimensionalArray)
        assert(resultArray.size == 0)
    }

    @Test
    fun `Check when array has only ones`() {
        val twoDimensionalArray = arrayOf(
            arrayOf(1, 1, 1),
            arrayOf(1, 1, 1),
            arrayOf(1, 1, 1),
            arrayOf(1, 1, 1)
        )
        val resultArray = runRiversAlgo(twoDimensionalArray)
        assert(resultArray.size == 1 && resultArray[0] == 12)
    }

    @Test
    fun `Check horizontal only river`() {
        val twoDimensionalArray = arrayOf(
            arrayOf(1, 1, 1),
            arrayOf(0, 0, 0),
            arrayOf(0, 0, 0),
            arrayOf(0, 0, 0)
        )
        val resultArray = runRiversAlgo(twoDimensionalArray)
        assert(resultArray.size == 1 && resultArray[0] == 3)
    }

    @Test
    fun `Check vertical only river`() {
        val twoDimensionalArray = arrayOf(
            arrayOf(0, 1, 0),
            arrayOf(0, 1, 0),
            arrayOf(0, 1, 0),
            arrayOf(0, 0, 0)
        )
        val resultArray = runRiversAlgo(twoDimensionalArray)
        assert(resultArray.size == 1 && resultArray[0] == 3)
    }

    @Test
    fun `Check if horizontal elements are considered a river`() {
        val twoDimensionalArray = arrayOf(
            arrayOf(1, 0, 1),
            arrayOf(0, 1, 0),
            arrayOf(1, 0, 1),
            arrayOf(0, 0, 0)
        )
        val resultArray = runRiversAlgo(twoDimensionalArray)
        assert(resultArray.size == 0)
    }

    @Test
    fun `Check if adjacent horizontal & vertical elements are being merged to same river`() {
        val twoDimensionalArray = arrayOf(
            arrayOf(1, 1, 1),
            arrayOf(0, 1, 0),
            arrayOf(1, 1, 1),
            arrayOf(1, 0, 0)
        )
        val resultArray = runRiversAlgo(twoDimensionalArray)
        assert(resultArray.size == 1 && resultArray[0] == 8)
    }

    @Test
    fun `Check if several rivers are being assigned correctly`() {
        val twoDimensionalArray = arrayOf(
            arrayOf(1, 1, 0),
            arrayOf(0, 1, 0),
            arrayOf(1, 0, 1),
            arrayOf(1, 1, 1)
        )
        val resultArray = runRiversAlgo(twoDimensionalArray)
        assert(resultArray.size == 2 && resultArray[0] == 3 && resultArray[1] == 5)
    }

}