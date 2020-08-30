package io.hvam.android.alphabet

/**
 * Alphabet is an interface for handling encoding and decoding of various UTF-8 characters, and
 * strings. The nature of each alphabet in implementation specific.
 */
interface Alphabet<T, R> {

    /**
     * Encode a string (usually a character) from one alphabet to another.
     */
    fun encode(character: R): T

    /**
     * Decode a string (usually a character) from one alphabet to another.
     */
    fun decode(character: T): R
}
