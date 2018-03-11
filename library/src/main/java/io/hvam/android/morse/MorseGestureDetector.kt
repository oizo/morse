package io.hvam.android.morse

import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.*

class MorseGestureDetector(val timeUnit: Long = 250L, val listener: OnCharacterListener) {

    companion object {
        val SHORT: Int = 1
        val LONG: Int = 2
        val WORD: Int = 3
    }

    private val handler = MorseHandler()
    private var dispatchWord: Boolean = false

    fun onTouchEvent(event: MotionEvent?): Boolean {

        Log.d(MorseGestureDetector::class.java.simpleName, "onTouchEvent() | " + event?.actionString())

        var handled = false
        if (event == null) {
            return handled
        }

        val action = event.action and ACTION_MASK
        when (action) {
            ACTION_DOWN -> {
                if (dispatchWord) {
                    dispatch(' ')
                    dispatchWord = false
                }
                cancel()
                handler.sendEmptyMessageDelayed(SHORT, timeUnit * 1)
                handler.sendEmptyMessageDelayed(LONG, timeUnit * 3)
                handled = true
            }
            ACTION_UP -> {
                if (handler.hasMessages(SHORT)) {
                    dispatch('.')
                } else if (handler.hasMessages(LONG)) {
                    dispatch('_')
                }
                cancel()
                handler.sendEmptyMessageDelayed(WORD, timeUnit * 3)
                handled = true
            }
            ACTION_CANCEL -> {
                cancel()
                handled = true
            }
        }

        return handled
    }

    private fun dispatch(c: Char) {
        listener.onCharacter(c)
    }

    private fun cancel() {
        handler.removeMessages(SHORT)
        handler.removeMessages(LONG)
        handler.removeMessages(WORD)
    }

    /**
     * Custom handler to handle dispatched messages
     */
    inner class MorseHandler : Handler() {

        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                MorseGestureDetector.SHORT -> {
                    // ignore
                }
                MorseGestureDetector.LONG -> {
                    this@MorseGestureDetector.dispatch('_')
                }
                MorseGestureDetector.WORD -> {
                    dispatchWord = true
                }
            }
        }

    }

    /**
     * The bridge between input events and morse code
     */
    interface OnCharacterListener {
        /**
         * Called when ever a new morse code character is detected, must be one of:
         *  - '.' (dot)
         *  - '_' (dash)
         *  - ' ' (space)
         */
        fun onCharacter(c: Char)
    }

}
