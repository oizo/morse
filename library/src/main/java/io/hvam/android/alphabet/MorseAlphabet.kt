package io.hvam.android.alphabet

import java.util.*


class MorseAlphabet: Alphabet {

    private val alphabet = listOf(
            // Letters
            "a" to "·−",
            "b" to "−···",
            "c" to "−·−·",
            "d" to "−··",
            "e" to "·",
            "f" to "··−·",
            "g" to "−−·",
            "h" to "····",
            "i" to "··",
            "j" to "·−−−",
            "k" to "−·−",
            "l" to "·−··",
            "n" to "−·",
            "m" to "−−",
            "o" to "−−−",
            "p" to "·−−·",
            "q" to "−−·−",
            "r" to "·−·",
            "s" to "···",
            "t" to "−",
            "u" to "··−",
            "v" to "···−",
            "w" to "·−−",
            "x" to "−··−",
            "y" to "−·−−",
            "z" to "−−··",
            // Numbers
            "1" to "·−−−−",
            "2" to "··−−−",
            "3" to "···−−",
            "4" to "····−",
            "5" to "·····",
            "6" to "−····",
            "7" to "−−···",
            "8" to "−−−··",
            "9" to "−−−−·",
            "0" to "−−−−−",
            // Punctuation
            "·" to "·−·−·−",
            "," to "−−··−−",
            "?" to "··−−··",
            "\'" to "·−−−−·",
            "!" to "−·−·−−",
            "/" to "−··−·",
            "(" to "−·−−·",
            ")" to "−·−−·−",
            "&" to "·−···",
            ":" to "−−−···",
            ";" to "−·−·−·",
            "=" to "−···−",
            "+" to "·−·−·",
            "−" to "−····−",
            "_" to "··−−·−",
            "\"" to "·−··−·",
            "$" to "···−··−",
            "@" to "·−−·−·",
            // Non−English extensions
            "å|à" to "·−−·−",
            "ä|æ|ą" to "·−·−",
            "ć|ĉ|ç" to "−·−··",
            "ch|ĥ|š" to "−−−−",
            "đ|é|ę" to "··−··",
            "ð" to "··−−·",
            "è|ł" to "·−··–",
            "ĝ" to "−−·−·",
            "ĵ" to "·−−−·",
            "ń|ñ" to "−−·−−",
            "ó|ö|ø" to "−−−·",
            "ś" to "···−···",
            "ŝ" to "···−·",
            "þ" to "·−−··",
            "ü|ŭ" to "··−−",
            "ź" to "−−··−·",
            "ż" to "−−··−"
    )

    override fun encode(character: String): String {
        val tmp = character.toLowerCase(Locale.US)
        alphabet.forEach { p ->
            p.first.split("|").find { c -> c == tmp }?.let{
                return p.second
            }
        }
        throw IllegalArgumentException("Unknown character: $character")
    }

    override fun decode(character: String): String {
        alphabet.forEach { p ->
            p.takeIf { p.second == character }?.let {
                return p.first.split("|")[0]
            }

        }
        throw IllegalArgumentException("Unknown character: $character")
    }
}