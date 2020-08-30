package io.hvam.android.morse.impl

import android.os.Handler
import android.os.Looper
import io.hvam.android.morse.MorseSymbol
import io.hvam.android.morse.MorseTimer

class MorseTimerStrict(
        timeUnit: Long = 250L,
        private val autoEndWord: Boolean = false,
        private val callback: (MorseSymbol) -> Unit
) : MorseTimer {

    private val timeDot = timeUnit * 1
    private val timeLetter = timeUnit * 3
    private val timeWord = timeUnit * 7
    private var active = false
    private var down = 0L
    private var up = 0L
    private val handler = Handler(Looper.getMainLooper())
    private val wordDelayRunner = Runnable {
        activate()
        active = false
    }

    override fun activate(): MorseSymbol? = when(active) {
        true -> throw IllegalStateException("MorseTimer must be deactivated before activating.")
        false -> {
            handler.removeCallbacks(wordDelayRunner)
            down = System.currentTimeMillis()
            active = true
            calcDown(down)?.also(callback)
        }
    }

    override fun deactivate(): MorseSymbol = when(active) {
        true -> {
            if (autoEndWord) {
                handler.postDelayed(wordDelayRunner, timeWord + 5)
            }
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