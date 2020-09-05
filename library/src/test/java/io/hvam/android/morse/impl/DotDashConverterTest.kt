package io.hvam.android.morse.impl

import io.hvam.android.morse.MorseSymbol
import org.junit.Test
import kotlin.test.assertEquals

class DotDashConverterTest {

    private val sut = DotDashConverter()

    @Test(expected = IllegalAccessException::class)
    fun testEncode() {
        sut.encode("this won't work")
    }

    @Test
    fun testDecode() {
        assertEquals("·", sut.decode(MorseSymbol.DOT))
        assertEquals("−", sut.decode(MorseSymbol.DASH))
        assertEquals("", sut.decode(MorseSymbol.CHAR_GAP))
        assertEquals(" ", sut.decode(MorseSymbol.LETTER))
        assertEquals(" / ", sut.decode(MorseSymbol.WORD))
    }

    @Test
    fun testDecodeList() {
        val tmp = listOf(
            MorseSymbol.DOT,
            MorseSymbol.DASH,
            MorseSymbol.CHAR_GAP,
            MorseSymbol.LETTER,
            MorseSymbol.WORD
        )
        assertEquals("·−  / ", sut.decode(tmp))
    }
}
