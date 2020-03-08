package io.hvam.android.morsedetectorsample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import io.hvam.android.morse.MorseGestureDetector
import io.hvam.android.morse.MorseSymbol
import io.hvam.android.morse.MorseTimer
import io.hvam.android.morse.impl.BinaryConverter
import io.hvam.android.morse.impl.MorseTimerImpl
import io.hvam.android.morse.impl.StringConverter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val binaryCallback = object : (MorseSymbol) -> Unit {
        val converter = BinaryConverter()
        override fun invoke(symbol: MorseSymbol) {
            val s = converter.convert(symbol).joinToString("", "", "")
            morseText.append(s)
        }
    }
    private val stringCallback = object : (MorseSymbol) -> Unit {
        val converter = StringConverter(letter = " ", word = "\n")
        override fun invoke(symbol: MorseSymbol) {
            val s = converter.convert(symbol)
            morseText.append(s)
        }
    }
    private val timer = MorseTimerImpl()
    private val gestureDetector = MorseGestureDetector(timer, stringCallback)
    private lateinit var nextRunner: NextRunner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        nextRunner = NextRunner(timer, morseNext)
        fab.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
    }

    override fun onStart() {
        super.onStart()
        nextRunner.start()
        morseText.text = getString(R.string.morse_text)
    }

    override fun onStop() {
        super.onStop()
        nextRunner.stop()
    }

}

class NextRunner(
        private val timer: MorseTimer,
        private val textView: TextView
): Runnable {

    companion object {
        const val INTERVAL = 16L
    }

    private val converter = StringConverter(letter = "letter", word = "word")
    private val handler = Handler(Looper.getMainLooper())

    fun start() {
        handler.postDelayed(this, INTERVAL)
    }

    fun stop() {
        handler.removeCallbacks(this)
    }

    override fun run() {
        val symbol = timer.next()
        val text = converter.convert(symbol)
        textView.text = textView.resources.getString(R.string.morse_next, text)
        handler.postDelayed(this, INTERVAL)
    }
}
