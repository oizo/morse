package io.hvam.android.morse

import android.view.MotionEvent
import com.shopgun.android.utils.ToStringUtils

fun MotionEvent.actionString(): String {
    return ToStringUtils.motionEventAction(action)
}
