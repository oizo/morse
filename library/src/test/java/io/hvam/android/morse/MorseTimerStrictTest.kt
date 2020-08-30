package io.hvam.android.morse

import io.hvam.android.morse.impl.MorseTimerStrict
import org.junit.Assert.*
import org.junit.Test

class MorseTimerStrictTest {

    private val mockTimeUnit = 10L
    private val mockCallback: (MorseSymbol) -> Unit = {}
    private val sut = MorseTimerStrict(mockTimeUnit, mockCallback)

    @Test(expected = IllegalStateException::class)
    fun testDoubleActivate() {
        sut.activate()
        sut.activate()
    }

    @Test(expected = IllegalStateException::class)
    fun testDoubleDeactivate() {
        sut.deactivate()
        sut.deactivate()
    }

//    @Test(expected = IllegalStateException::class)
    fun testTooQuick() {
        sut.activate()
        sut.deactivate()
        sut.activate()
        sut.deactivate()
    }

    @Test
    fun testDot() {
        assertNull(sut.activate())
        assertEquals(MorseSymbol.DOT, sut.deactivate())
    }

    @Test
    fun testDash() {
        sut.activate()
        Thread.sleep(mockTimeUnit)
        assertEquals(MorseSymbol.DASH, sut.deactivate())
    }

    @Test
    fun testLetter() {
        sut.activate()
        sut.deactivate()
        Thread.sleep(mockTimeUnit * 3)
        assertEquals(MorseSymbol.LETTER, sut.activate())
    }

    @Test
    fun testWord() {
        sut.activate()
        sut.deactivate()
        Thread.sleep((mockTimeUnit * 7) + 5)
        assertEquals(MorseSymbol.WORD, sut.activate())
    }

    @Test
    fun activationSequence() {
        // Dot quick
        assertNull(sut.activate())
        assertEquals(MorseSymbol.DOT, sut.deactivate())

        // Dot slow
        sut.activate()
        Thread.sleep(mockTimeUnit-5)
        assertEquals(MorseSymbol.DOT, sut.deactivate())

        // Dash quick
        sut.activate()
        Thread.sleep(mockTimeUnit)
        assertEquals(MorseSymbol.DASH, sut.deactivate())

        // Dash slow
        sut.activate()
        Thread.sleep((mockTimeUnit * 3) - 5)
        assertEquals(MorseSymbol.DASH, sut.deactivate())

        // Wait long enough for Word on activate event
        Thread.sleep(mockTimeUnit * 8)
        assertEquals(MorseSymbol.WORD, sut.activate())

        assertEquals(MorseSymbol.DOT, sut.deactivate())

    }

}