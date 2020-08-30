package io.hvam.android.alphabet

import org.junit.Test
import kotlin.test.assertEquals

class PhoneticNatoAlphabetTest {

    @Test
    fun encode() {
        val sut = PhoneticNatoAlphabet.danish()
        assertEquals("alfa", sut.encode("a"))
    }

    @Test
    fun decode() {
        val sut = PhoneticNatoAlphabet.danish()
        assertEquals("a", sut.decode("alfa"))
    }

    @Test
    fun countryDifferentiation() {
        assertEquals("ødis", PhoneticNatoAlphabet.danish().encode("ø"))
        assertEquals("ørnulf", PhoneticNatoAlphabet.norwegianCivilian().encode("ø"))
        assertEquals("østen", PhoneticNatoAlphabet.norwegianMilitary().encode("ø"))
    }
}
