package com.bsvt.customview.externals

import com.bsvt.customview.Petal

fun Int.toAngleEnum(): Petal.Angle {
    return when(this) {
        0 -> Petal.Angle.Angle_8
        1 -> Petal.Angle.Angle_16
        else -> Petal.Angle.Angle_16
    }
}

fun Int.toPower(): Float {
    return when (this) {
        0 -> 0f
        1 -> 0.1f
        2 -> 0.2f
        3 -> 0.3f
        4 -> 0.4f
        5 -> 0.5f
        6 -> 0.6f
        7 -> 0.7f
        8 -> 0.8f
        9 -> 0.9f
        10 -> 1f
        else -> 1f
    }
}

fun Int.toDirection(): Petal.Direction {
    return when (this) {
        0 ->  Petal.Direction.N
        1 ->  Petal.Direction.NNE
        2 ->  Petal.Direction.NE
        3 ->  Petal.Direction.ENE
        4 ->  Petal.Direction.E
        5 ->  Petal.Direction.ESE
        6 ->  Petal.Direction.SE
        7 ->  Petal.Direction.SSE
        8 ->  Petal.Direction.S
        9 ->  Petal.Direction.SSW
        10 -> Petal.Direction.SW
        11 -> Petal.Direction.WSW
        12 -> Petal.Direction.W
        13 -> Petal.Direction.WNW
        14 -> Petal.Direction.NW
        15 -> Petal.Direction.NNW
        else -> Petal.Direction.N
    }
}

fun  Int.toTopStyle(): Petal.TopStyle {
    return when (this) {
        0 -> Petal.TopStyle.Flat
        1 -> Petal.TopStyle.Sector
        else -> Petal.TopStyle.Flat
    }
}

fun Int.toBottomStyle(): Petal.BottomStyle {
    return when (this) {
        0 -> Petal.BottomStyle.Flat
        1 -> Petal.BottomStyle.Sector
        else -> Petal.BottomStyle.Flat
    }
}