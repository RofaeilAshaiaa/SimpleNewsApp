package com.example.newsproject.utils

import org.junit.Assert.*
import org.junit.Test

class UtilsKtTest {

    @Test
    fun `getDateTimeFormatted() with null dateTime as parameter, then return null`() {
        //arrange
        val timeEpoch: String? = null
        //act
        val result = getDateTimeFormatted(timeEpoch)
        //assert
        assertNull(result)
    }

    @Test
    fun `getDateTimeFormatted() with incorrect dateTime as parameter, then return null`() {
        //arrange
        val timeEpoch = "2023-01-26Tghs20:41:00Z"
        //act
        val result = getDateTimeFormatted(timeEpoch)
        //assert
        assertNull(result)
    }

    @Test
    fun `getDateTimeFormatted() with correct dateTime as parameter, then return correct formatted dateTime`() {
        //arrange
        val timeEpoch = "2023-01-26T20:41:00Z"
        val expectedFormattedDateTime = "Thu, 26 Jan 2023  |  08:41 PM"
        //act
        val result = getDateTimeFormatted(timeEpoch)
        //assert
        assertNotNull(result)
        assertEquals(expectedFormattedDateTime, result)
    }

    @Test
    fun `getDateTimeFormatted() with correct dateTime as parameter with timezone character as t lower case, then return correct formatted dateTime`() {
        //arrange
        val timeEpoch = "2023-01-26t20:41:00Z"
        val expectedFormattedDateTime = "Thu, 26 Jan 2023  |  08:41 PM"
        //act
        val result = getDateTimeFormatted(timeEpoch)
        //assert
        assertNotNull(result)
        assertEquals(expectedFormattedDateTime, result)
    }
}