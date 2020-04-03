package io.hvam.android.alphabet

import java.util.*

class PhoneticNatoAlphabet: Alphabet {

    private val alphabet = listOf(
            "a" to "alfa",
            "b" to "bravo",
            "c" to "charlie",
            "d" to "delta",
            "e" to "echo",
            "f" to "foxtrot",
            "g" to "golf",
            "h" to "hotel",
            "i" to "india",
            "j" to "juliett",
            "k" to "kilo",
            "l" to "lima",
            "m" to "mike",
            "n" to "november",
            "o" to "oscar",
            "p" to "papa",
            "q" to "quebec",
            "r" to "romeo",
            "s" to "sierra",
            "t" to "tango",
            "u" to "uniform",
            "v" to "victor",
            "w" to "whiskey",
            "x" to "x-ray",
            "y" to "yankee",
            "z" to "zulu"
    )

    private val alphabetDa = listOf(
            "æ" to "ægir",
            "ø" to "ødis",
            "å" to "åse"
    )

    private val alphabetNoCivil = listOf(
            "æ" to "ægir",
            "ø" to "ørnulf",
            "å" to "ågot"
    )

    private val alphabetNoMili = listOf(
            "æ" to "ærlig",
            "ø" to "østen",
            "å" to "åse"
    )

    private val alphabetSe = listOf(
            "ä" to "ärlig",
            "ö" to "östen",
            "å" to "åke"
    )

    override fun encode(character: String): String {
        val tmp = character.toLowerCase(Locale.US)
        alphabet.forEach { p ->
            if (p.first == tmp ) {
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
        return ""
    }
}