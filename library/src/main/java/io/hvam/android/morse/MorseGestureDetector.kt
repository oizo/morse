package io.hvam.android.morse

import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View

class MorseGestureDetector(
        private val timer: MorseTimer
): View.OnTouchListener {

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return onTouchEvent(event)
    }

    fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.actionMasked ?: -1) {
            ACTION_DOWN -> {
                timer.activate()
                true
            }
            ACTION_UP -> {
                timer.deactivate()
                true
            }
            else -> false
        }

    }

}
