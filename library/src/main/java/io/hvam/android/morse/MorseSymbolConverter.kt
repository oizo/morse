package io.hvam.android.morse

interface MorseSymbolConverter {
    fun decode(symbol: MorseSymbol): String
    fun decode(symbol: List<MorseSymbol>): String
    fun encode(text: String): List<MorseSymbol>
}
