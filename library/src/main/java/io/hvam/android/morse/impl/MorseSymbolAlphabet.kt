package io.hvam.android.morse.impl

import io.hvam.android.alphabet.Alphabet
import io.hvam.android.alphabet.MorseAlphabet
import io.hvam.android.morse.MorseSymbol
import io.hvam.android.morse.MorseSymbolConverter
import io.hvam.android.morse.split

class MorseSymbolAlphabet(
        private val converter: MorseSymbolConverter = StringConverter(MorseAlphabet.DOT, MorseAlphabet.DASH, E, E, S),
        private val alphabet: Alphabet<String, String> = MorseAlphabet()
): Alphabet<List<MorseSymbol>, String> {

    companion object {
        /** Empty string */
        private const val E = ""
        /** Space string */
        private const val S = " "
    }

    override fun encode(text: String): List<MorseSymbol> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun decode(symbols: List<MorseSymbol>): String =
            symbols.split { it == MorseSymbol.WORD }.joinToString(S, E, E) { word ->
                word.split { it == MorseSymbol.LETTER }.joinToString(E, E, S) { letter ->
                    alphabet.decode(converter.decode(letter))
                }
            }.trim()

}
