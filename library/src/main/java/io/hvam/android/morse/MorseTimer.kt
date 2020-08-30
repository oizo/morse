package io.hvam.android.morse

/**
 * @see <a href="https://en.wikipedia.org/wiki/Morse_code">Wikipedia: Morse Code</a>
 */
interface MorseTimer {
    fun activate(): MorseSymbol?
    fun deactivate(): MorseSymbol
    fun next(): MorseSymbol
}
