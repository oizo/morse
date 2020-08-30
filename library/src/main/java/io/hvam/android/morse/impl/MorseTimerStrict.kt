package io.hvam.android.morse.impl

import io.hvam.android.morse.MorseSymbol
import io.hvam.android.morse.MorseTimer

class MorseTimerStrict(
        timeUnit: Long = 250L,
        private val callback: (MorseSymbol) -> Unit
) : MorseTimer {

    val timeDot = timeUnit * 1
    val timeLetter = timeUnit * 3
    val timeWord = timeUnit * 7
    private var active = false
    private var down = 0L
    private var up = 0L

    override fun activate(): MorseSymbol? = when(active) {
        true -> throw IllegalStateException("MorseTimer must be deactivated before activating.")
        false -> {
            down = System.currentTimeMillis()
            active = true
            calcDown(down)?.also(callback)
        }
    }

    override fun deactivate(): MorseSymbol = when(active) {
        true -> {
            up = System.currentTimeMillis()
            active = false
            calcUp(up).also(callback)
        }
        false -> throw IllegalStateException("MorseTimer must be activated before deactivating.")
    }

    override fun next(): MorseSymbol =
            if (active) {
                calcUp(System.currentTimeMillis())
            } else {
                calcDown(System.currentTimeMillis()) ?: MorseSymbol.DOT
            }

    private fun calcDown(down: Long): MorseSymbol? = if (up > 0L) {
        val delta = down - up
        when {
            delta > timeWord -> MorseSymbol.WORD
            delta > timeLetter -> MorseSymbol.LETTER
            else -> MorseSymbol.CHAR_GAP
        }
    } else null

    private fun calcUp(up: Long): MorseSymbol =
            if (up - down < timeDot) MorseSymbol.DOT else MorseSymbol.DASH

}