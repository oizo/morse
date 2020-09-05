package io.hvam.android.morse

import android.view.MotionEvent
import android.view.View
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import org.junit.Test

class MorseGestureDetectorTest {

    private val mockTimer = mock<MorseTimer>{}
    private val sut = MorseGestureDetector(mockTimer)

    @Test
    fun testTouch() {
        val mockView = mock<View>{ }
        val mockEvent = mock<MotionEvent>{ }
        val spy = spy(sut)
        spy.onTouch(mockView, mockEvent)
        verify(spy).onTouchEvent(mockEvent)
    }

    @Test
    fun testTouchEventDown() {
        val event = mock<MotionEvent> {
            on { actionMasked } doReturn MotionEvent.ACTION_DOWN
        }
        sut.onTouchEvent(event)
        verify(mockTimer).deactivate()
    }

    @Test
    fun testTouchEventUp() {
        val event = mock<MotionEvent> {
            on { actionMasked } doReturn MotionEvent.ACTION_UP
        }
        sut.onTouchEvent(event)
        verify(mockTimer).activate()
    }

    @Test
    fun tezstTouchEventNull() {
        sut.onTouchEvent(null)
        verifyZeroInteractions(mockTimer)
    }

}