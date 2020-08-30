package io.hvam.android.morse.impl

import io.hvam.android.morse.MorseSymbol
import io.hvam.android.morse.MorseSymbolConverter

open class StringConverter(
    private val dot: String = "1",
    private val dash: String = "111",
    private val charGap: String = "0",
    private val letter: String = "000",
    private val word: String = "0000000"
) : MorseSymbolConverter {

    /**
     * // Sos must return in binary form
     * 10101000101010001010100011100011100011100010101
     * ...---...
     */
    override fun encode(text: String): List<MorseSymbol> {
        TODO("Implement the encoding of string to MorseSymbols")
    }

    private fun Char.asMorseSymbol() = when (toString()) {
        dot -> MorseSymbol.DOT
        dash -> MorseSymbol.DASH
        charGap -> MorseSymbol.CHAR_GAP
        letter -> MorseSymbol.LETTER
        word -> MorseSymbol.WORD
        else -> throw IllegalArgumentException("Unknown character: $this")
    }

    override fun decode(symbol: MorseSymbol): String = symbol.toMorseString()

    override fun decode(symbol: List<MorseSymbol>): String =
        symbol.joinToString("", "", "") { it.toMorseString() }

    private fun MorseSymbol.toMorseString() = when (this) {
        MorseSymbol.DOT -> dot
        MorseSymbol.DASH -> dash
        MorseSymbol.CHAR_GAP -> charGap
        MorseSymbol.LETTER -> letter
        MorseSymbol.WORD -> word
    }
}
