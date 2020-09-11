package io.hvam.android.morse.impl

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import io.hvam.android.alphabet.Alphabet
import io.hvam.android.morse.MorseSymbol
import io.hvam.android.morse.MorseSymbolConverter
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MorseSymbolAlphabetTest {

    private val mockConverter = mock<MorseSymbolConverter> {
        on { decode(any<List<MorseSymbol>>()) } doReturn "·−"
    }
    private val mockAlphabet = mock<Alphabet<String, String>> {
        on { decode(any()) } doReturn "a"
    }
    private val sut = MorseSymbolAlphabet(mockConverter, mockAlphabet)

    @Test
    fun testEmpty() {
        val tmp = sut.decode(listOf())
        verifyZeroInteractions(mockConverter)
        verifyZeroInteractions(mockAlphabet)
        assertTrue { tmp.isEmpty() }
    }

    @Test
    fun testWord() {
        val symbolList = listOf(MorseSymbol.DOT, MorseSymbol.DASH)
        val tmp = sut.decode(symbolList)
        verify(mockConverter).decode(any<List<MorseSymbol>>())
        verify(mockAlphabet).decode(any())
        assertEquals("a", tmp)
    }

    @Test
    fun testMultipleWords() {
        val symbolList = listOf(MorseSymbol.DOT, MorseSymbol.DASH, MorseSymbol.WORD, MorseSymbol.DOT, MorseSymbol.DASH)
        val tmp = sut.decode(symbolList)
        verify(mockConverter, times(2)).decode(any<List<MorseSymbol>>())
        verify(mockAlphabet, times(2)).decode(any())
        assertEquals("a a", tmp)
    }
}
