package io.hvam.android.morse

interface MorseCodec {
    fun encode(text: Char): Char
    fun encode(text: String): String
    fun decode(morse: Char): Char
    fun decode(morse: String): String
}