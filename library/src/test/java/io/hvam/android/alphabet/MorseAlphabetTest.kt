package io.hvam.android.alphabet

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MorseAlphabetTest {

    @Test
    fun encode() {
        val sut = MorseAlphabet()
        assertEquals("·−", sut.encode("a"))
        assertEquals("·−", sut.encode("A"))
    }

    @Test
    fun decode() {
        val sut = MorseAlphabet()
        assertEquals("a", sut.decode("·−"))
        assertEquals(",", sut.decode("−−··−−"))
    }

    @Test
    fun multiLetters() {
        val sut = MorseAlphabet()
        // Three different letters can return the same encoding
        assertEquals("−·−··", sut.encode("ç"))
        assertEquals("−·−··", sut.encode("ĉ"))
        assertEquals("−·−··", sut.encode("ć"))
        // But the encoding will always return the same letter
        assertEquals("ć", sut.decode("−·−··"))
        assertNotEquals("ĉ", sut.decode("−·−··"))
        assertNotEquals("ç", sut.decode("−·−··"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun encodeInvalidChar() {
        MorseAlphabet().encode("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun decodeInvalidChar() {
        MorseAlphabet().decode("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun decodeInvalidString() {
        // sos
        MorseAlphabet().decode("···−−−···")
    }
}
