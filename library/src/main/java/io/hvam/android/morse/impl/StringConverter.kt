package io.hvam.android.morse.impl

import io.hvam.android.morse.MorseConverter
import io.hvam.android.morse.MorseSymbol

class StringConverter(
        private val dot: String = "·",
        private val dash: String = "−",
        private val charGap: String = "",
        private val letter: String = "",
        private val word: String = " "
): MorseConverter<String> {

    override fun convert(vararg symbol: MorseSymbol): String =
            symbol.joinToString { it.toMorseString() }

    private fun MorseSymbol.toMorseString() = when (this) {
        MorseSymbol.DOT -> dot
        MorseSymbol.DASH -> dash
        MorseSymbol.CHAR_GAP -> charGap
        MorseSymbol.LETTER -> letter
        MorseSymbol.WORD -> word
    }

}
