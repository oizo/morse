package io.hvam.android.alphabet

import java.util.*

class MorseDanishAlphabet: Alphabet {

    val alphabet = mutableListOf<Pair<String, String>>().apply {
        add("a" to "·−")
        add("b" to "−···")
        add("c" to "−·−·")
        add("d" to "−··")
        add("e" to "·")
        add("é|đ|é|ę" to "··−··")
        add("f" to "··−·")
        add("g" to "−−·")
        add("h" to "····")
        add("i" to "··")
        add("j" to "·−−−")
        add("k" to "−·−")
        add("l" to "·−··")
        add("n" to "−·")
        add("m" to "−−")
        add("o" to "−−−")
        add("p" to "·−−·")
        add("q" to "−−·−")
        add("r" to "·−·")
        add("s" to "···")
        add("t" to "−")
        add("u" to "··−")
        add("v" to "···−")
        add("w" to "·−−")
        add("x" to "−··−")
        add("y" to "−·−−")
        add("z" to "−−··")
        add("æ|ä|ą" to "·−·−")
        add("ø|ó|ö" to "−−−·")
        add("å|à" to "·−−·−")
        add("1" to "·−−−−")
        add("2" to "··−−−")
        add("3" to "···−−")
        add("4" to "····−")
        add("5" to "·····")
        add("6" to "−····")
        add("7" to "−−···")
        add("8" to "−−−··")
        add("9" to "−−−−·")
        add("0" to "−−−−−")
        add("ch|ĥ|š" to "−−−−")
        add("è|ł" to "·−··–")
        add("ñ|ń" to "−−·−−")
        add("ç|ĉ|ć" to "−·−··")
        add("ĝ" to "−−·−·")
        add("ŝ" to "···−·")
        add("þ" to "·−−··")
        add("ð" to "··−−·")
        add("ĵ" to "·−−−·")
        add("ü|ŭ" to "··−−")
        add("ś" to "···−···")
        add("ź" to "−−··−·")
        add("ż" to "−−··−")
        add("." to "·−·−·−")
        add("," to "−−··−−")
        add(":" to "−−−···")
        add("?" to "··−−··")
        add("\'" to "·−−−−·")
        add("-" to "−····−")
        add("/" to "−··−·")
        add("(" to "−·−−·")
        add(")" to "−·−−·−")
        add("\"" to "·−··−·")
        add("\\n|aa" to "·−·−")
        add("+|ar" to "·−·−·")
        add("as" to "·−···")
        add("=|bt" to "−···−")
        add("ka|ct" to "−·−·−")
        add("hh|eeeeeeee" to "········")
        add("kn" to "−·−−·")
        add("nj" to "−··−−−")
        add("sk" to "···−·−")
        add("sn|ve" to "···−·")
        add("@" to "·−−·−·")
    }


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
        return ""
    }
}