package com.radiance.customview.windDirections.petal

import android.graphics.Path

fun Path.lineTo(d: Double, d1: Double) {
    lineTo(d.toFloat(), d1.toFloat())
}

fun Path.rMoveTo(startX: Double, startY: Double) {
    rMoveTo(startX.toFloat(), startY.toFloat())
}

fun Path.moveTo(d: Double, d1: Double) {
    moveTo(d.toFloat(), d1.toFloat())
}

fun Path.rLineTo(d: Double, d1: Double) {
    rLineTo(d.toFloat(), d1.toFloat())
}