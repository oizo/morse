package io.hvam.android.alphabet

/**
 * Alphabet defined methods for encoding and decoding symbols between alphabets.
 *
 * An example could be the encoding and decoding between the latin alphabet,
 * to the phonetic alphabet, where we can define the actions as:
 *
 *  - [encode]: a -> alfa, and vice versa
 *  - [decode]: alfa -> a
 *
 * An [Alphabet] must not translate or interpret the symbols.
 *
 * @param D a decoded symbol
 * @param E an encoded symbol
 */
interface Alphabet<E, D> {

    /**
     * Encode a [symbol] from one alphabet to another.
     */
    fun encode(symbol: D): E

    /**
     * Decode a [symbol] from one alphabet to another.
     */
    fun decode(symbol: E): D
}
