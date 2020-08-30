package io.hvam.android.alphabet

import java.util.*

class PhoneticNatoAlphabet private constructor(
        private val alphabet: Map<String, String>
): Alphabet<String, String> {

    companion object {

        private val alphabetIntl = mapOf(
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

        private val alphabetDa = mapOf(
                "æ" to "ægir",
                "ø" to "ødis",
                "å" to "åse"
        )

        private val alphabetNoCivil = mapOf(
                "æ" to "ægir",
                "ø" to "ørnulf",
                "å" to "ågot"
        )

        private val alphabetNoMili = mapOf(
                "æ" to "ærlig",
                "ø" to "østen",
                "å" to "åse"
        )

        private val alphabetSe = mapOf(
                "ä" to "ärlig",
                "ö" to "östen",
                "å" to "åke"
        )

        private fun create(map: Map<String, String>) =
                PhoneticNatoAlphabet(mutableMapOf<String, String>().apply {
                    putAll(alphabetIntl)
                    putAll(map)
                })

        fun danish() = create(alphabetDa)

        fun swedish() = create(alphabetSe)

        fun norwegianCivilian() = create(alphabetNoCivil)

        fun norwegianMilitary() = create(alphabetNoMili)

    }

    override fun encode(character: String): String {
        val tmp = character.toLowerCase(Locale.US)
        for ((k, v) in alphabet) { if (k == tmp) return v }
        throw IllegalArgumentException("Unknown character: $character")
    }

    override fun decode(character: String): String {
        for ((k, v) in alphabet) { if (v == character) return k }
        throw IllegalArgumentException("Unknown character: $character")
    }
}