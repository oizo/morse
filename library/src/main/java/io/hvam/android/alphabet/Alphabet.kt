package io.hvam.android.alphabet

interface Alphabet {
    fun encode(character: String): String
    fun decode(character: String): String
}