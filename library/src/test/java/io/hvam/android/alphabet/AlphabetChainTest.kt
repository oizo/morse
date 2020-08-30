package io.hvam.android.alphabet

import org.junit.Test
import kotlin.test.assertEquals

class AlphabetChainTest {

    @Test
    fun chain() {
        val expected = "alfa"
        val phoneticDecode = PhoneticNatoAlphabet.danish().decode(expected)
        assertEquals("a", phoneticDecode)
        val morseEncode = MorseAlphabet().encode(phoneticDecode)
        assertEquals("·−", morseEncode)
        val morseDecode = MorseAlphabet().decode(morseEncode)
        val phoneticEncode = PhoneticNatoAlphabet.danish().encode(morseDecode)
        assertEquals(expected, phoneticEncode)
    }
}
