package io.hvam.android.morse

interface MorseConverter<T> {
    fun convert(vararg symbol: MorseSymbol): T
}