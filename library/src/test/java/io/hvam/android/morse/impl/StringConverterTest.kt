package io.hvam.android.morse.impl

import io.hvam.android.morse.MorseSymbol
import org.junit.Assert
import org.junit.Test

class StringConverterTest {

    private val sut = StringConverter()

    @Test
    fun testCharacters() {
        val a = listOf(MorseSymbol.DOT, MorseSymbol.CHAR_GAP, MorseSymbol.DASH)
        Assert.assertEquals("10111", sut.decode(a))

        val e = listOf(MorseSymbol.DOT)
        Assert.assertEquals("1", sut.decode(e))

        val z = listOf(MorseSymbol.DASH, MorseSymbol.CHAR_GAP, MorseSymbol.DASH, MorseSymbol.CHAR_GAP, MorseSymbol.DOT, MorseSymbol.CHAR_GAP, MorseSymbol.DOT)
        Assert.assertEquals("11101110101", sut.decode(z))
    }

    @Test
    fun testWord() {
        // sos
        val word = listOf(
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.LETTER,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.LETTER,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT
        )
        Assert.assertEquals("101010001110111011100010101", sut.decode(word))
    }

    @Test
    fun testSentence() {
        // hvam.io is cool
        val sentence = listOf(
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.LETTER,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.LETTER,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.LETTER,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.LETTER,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.LETTER,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.LETTER,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.WORD,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.LETTER,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.WORD,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.LETTER,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.LETTER,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.LETTER,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.DOT
        )
        val expected = "101010100010101011100010111000111011100010111010111010111000101000111011101110000000101000101010000000111010111010001110111011100011101110111000101110101"
        Assert.assertEquals(expected, sut.decode(sentence))
    }

    @Test(expected = NotImplementedError::class)
    fun test() {
        sut.encode("exception")
    }
}
