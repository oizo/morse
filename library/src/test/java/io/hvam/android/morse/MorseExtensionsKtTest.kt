package io.hvam.android.morse

import org.junit.Test
import kotlin.test.assertEquals

class MorseExtensionsKtTest {

    @Test
    fun testListSplitInclude() {
        val list = listOf(1, 2, 3, 1, 2, 3, 1, 2, 3)
        val exclude = list.split(true) { it % 3 == 0 }
        assertEquals(3, exclude.size)
        exclude.forEach { assertEquals(3, it.size) }
    }

    @Test
    fun testListSplitExclude() {
        val list = listOf(1, 2, 3, 1, 2, 3, 1, 2, 3)
        val include = list.split(false) { it % 3 == 0 }
        assertEquals(3, include.size)
        include.forEach { assertEquals(2, it.size) }
    }

    @Test
    fun testListSplitBufferInclude() {
        val list = arrayListOf(1, 2, 3, 1, 2, 3, 1, 2, 3)
        val buffer = arrayListOf<List<Int>>()
        list.split(buffer, true) { it % 3 == 0 }
        assertEquals(3, buffer.size)
        buffer.forEach { assertEquals(3, it.size) }
    }

    @Test
    fun testListSplitBufferExclude() {
        val list = arrayListOf(1, 2, 3, 1, 2, 3, 1, 2, 3)
        val buffer = arrayListOf<List<Int>>()
        list.split(buffer, false) { it % 3 == 0 }
        assertEquals(3, buffer.size)
        buffer.forEach { assertEquals(2, it.size) }
    }
}
