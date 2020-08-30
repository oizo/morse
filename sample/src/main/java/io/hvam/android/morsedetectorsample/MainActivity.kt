package io.hvam.android.morsedetectorsample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.hvam.android.morse.MorseGestureDetector
import io.hvam.android.morse.MorseSymbol
import io.hvam.android.morse.MorseTimer
import io.hvam.android.morse.impl.MorseTimerStrict
import io.hvam.android.morse.impl.DotDashConverter
import io.hvam.android.morse.impl.MorseSymbolAlphabet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val dotDashConverter = DotDashConverter()
    private val alphabet = MorseSymbolAlphabet()
    private val symbols = mutableListOf<MorseSymbol>()
    private val handler = Handler()
    private val updateWord = Runnable {
        morseText.text = alphabet.decode(symbols)
    }
    private val callback: (MorseSymbol) -> Unit = {
        handler.removeCallbacks(updateWord)
        symbols.add(it)
        val s = dotDashConverter.decode(it)
        morseCode.append(s)
        if (it == MorseSymbol.LETTER || it == MorseSymbol.WORD) {
            updateWord.run()
        } else {
            handler.postDelayed(updateWord, timer.timeWord)
        }
    }

    private val timer = MorseTimerStrict(callback = callback)
    private lateinit var nextRunner: NextRunner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        nextRunner = NextRunner(timer, morseNext)
        fab.setOnTouchListener(MorseGestureDetector(timer))
    }

    override fun onStart() {
        super.onStart()
        nextRunner.start()
        morseCode.text = getString(R.string.morse_text)
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

    private val converter = DotDashConverter(letter = "letter", word = "word")
    private val handler = Handler(Looper.getMainLooper())

    fun start() {
        handler.postDelayed(this, INTERVAL)
    }

    fun stop() {
        handler.removeCallbacks(this)
    }

    override fun run() {
        val symbol = timer.next()
        val text = converter.decode(symbol)
        textView.text = textView.resources.getString(R.string.morse_next, text)
        handler.postDelayed(this, INTERVAL)
    }
}
