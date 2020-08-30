package io.hvam.android.morse.impl

import io.hvam.android.alphabet.MorseAlphabet
import io.hvam.android.morse.MorseSymbol

class DotDashConverter(
    dot: String = MorseAlphabet.DOT,
    dash: String = MorseAlphabet.DASH,
    charGap: String = "",
    letter: String = " ",
    word: String = " / "
) : StringConverter(
    dot, dash, charGap, letter, word
) {

    override fun encode(text: String): List<MorseSymbol> {
        throw IllegalAccessException("${javaClass.simpleName} doesn't support encoding")
    }
}
