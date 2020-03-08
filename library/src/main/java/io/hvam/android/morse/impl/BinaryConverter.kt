package io.hvam.android.morse.impl

import io.hvam.android.morse.MorseConverter
import io.hvam.android.morse.MorseSymbol

class BinaryConverter: MorseConverter<List<Int>> {

    companion object {
        private val DOT = listOf(1)
        private val DASH = listOf(1, 1, 1)
        private val CHAR_GAP = listOf(0)
        private val LETTER = listOf(0, 0, 0)
        private val WORD = listOf(0, 0, 0, 0, 0, 0, 0)
    }

    override fun convert(vararg symbol: MorseSymbol): List<Int> =
            symbol.flatMap { it.toMorseString() }

    private fun MorseSymbol.toMorseString() = when (this) {
        MorseSymbol.DOT -> DOT
        MorseSymbol.DASH -> DASH
        MorseSymbol.CHAR_GAP -> CHAR_GAP
        MorseSymbol.LETTER -> LETTER
        MorseSymbol.WORD -> WORD
    }

}
