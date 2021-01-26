package com.rithikjain.portfolio.utils

import android.graphics.Bitmap
import android.graphics.Matrix

fun Bitmap.rotate(angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
}