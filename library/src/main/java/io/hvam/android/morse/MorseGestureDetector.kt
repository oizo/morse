package io.hvam.android.morse

import android.view.MotionEvent
import android.view.MotionEvent.*

class MorseGestureDetector(
        private val timer: MorseTimer,
        private val callback: (MorseSymbol) -> Unit
) {

    fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.actionMasked ?: -1) {
            ACTION_DOWN -> {
                timer.activate()?.let { callback.invoke(it) }
                true
            }
            ACTION_UP -> {
                callback.invoke(timer.deactivate())
                true
            }
            else -> false
        }

    }

}
