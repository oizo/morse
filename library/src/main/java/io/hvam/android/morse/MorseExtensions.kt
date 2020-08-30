package io.hvam.android.morse


/**
 * Splits a [List], into a multidimensional [List]. The split will be performed
 * whenever [split] is true. If [include] is true the element where the split
 * occurs will be included at the end of the current.
 */
fun <T, R: List<List<T>>> List<T>.split(include: Boolean = false, split: (T) -> Boolean): R {
    return split(mutableListOf(), include, split) as R
}

/**
 * Splits a [Collection] into pieces, and appends each piece to [buffer]. The split will be performed
 * whenever [split] is true. If [include] is true the element where the split occurs will be included
 * at the end of the current.
 */
fun <T, R: MutableList<List<T>>> List<T>.split(buffer: R, include: Boolean = false, split: (T) -> Boolean): R {
    var piece = mutableListOf<T>()
    forEach {
        when (split.invoke(it)) {
            true -> {
                if (include) piece.add(it)
                buffer.add(piece)
                piece = mutableListOf()
            }
            false -> piece.add(it)
        }
    }
    if (piece.isNotEmpty()) buffer.add(piece)
    return buffer
}
