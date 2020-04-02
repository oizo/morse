package io.hvam.android.alphabet

import org.junit.Test

import org.junit.Assert.*

class MorseDanishAlphabetTest {

    @Test
    fun encode() {
        val sut = MorseDanishAlphabet()
        assertEquals("·−", sut.encode("a"))
        assertEquals("·−", sut.encode("A"))
        assertEquals("········", sut.encode("eeeeeeee"))
        assertEquals("········", sut.encode("EEEEEEEE"))
    }

    @Test
    fun decode() {
        val sut = MorseDanishAlphabet()
        assertEquals("a", sut.decode("·−"))
        assertEquals(",", sut.decode("−−··−−"))
    }

    @Test
    fun multiLetters() {
        val sut = MorseDanishAlphabet()
        // Three different letters can return the same encoding
        assertEquals("−·−··", sut.encode("ç"))
        assertEquals("−·−··", sut.encode("ĉ"))
        assertEquals("−·−··", sut.encode("ć"))
        // But the encoding will always return the same letter
        assertEquals("ç", sut.decode("−·−··"))
        assertNotEquals("ĉ", sut.decode("−·−··"))
        assertNotEquals("ć", sut.decode("−·−··"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun encodeInvalidChar() {
        val x = MorseDanishAlphabet().encode("")
        assertEquals("dsa", x)
    }

}