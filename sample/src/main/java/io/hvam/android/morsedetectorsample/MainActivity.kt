package io.hvam.android.morsedetectorsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.hvam.android.morse.MorseGestureDetector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var morseDetector: MorseGestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        morseDetector = MorseGestureDetector(listener = object : MorseGestureDetector.OnCharacterListener {
            override fun onCharacter(c: Char) {
                morseText.append(c.toString())
            }
        })

        fab.setOnTouchListener { v, event -> morseDetector.onTouchEvent(event) }
    }

    override fun onStart() {
        super.onStart()
        morseText.text = getString(R.string.morse_text)
    }

}
