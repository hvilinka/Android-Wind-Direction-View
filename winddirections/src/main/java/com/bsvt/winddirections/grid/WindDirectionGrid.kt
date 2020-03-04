package com.bsvt.winddirections.grid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class WindDirectionGrid(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mainPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mainPaint.color = Color.BLACK
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = 1f

        val path = Path()

        val minRadius = (if (width <= height) width.toFloat() else height.toFloat()) / 2

        val centerX = width / 2f
        val centerY = height / 2f

        path.addCircle(centerX, centerY, minRadius, Path.Direction.CW)
        path.addCircle(centerX, centerY, minRadius * 0.9F, Path.Direction.CW)
        path.addCircle(centerX, centerY, minRadius * 0.6f, Path.Direction.CW)
        path.addCircle(centerX, centerY, minRadius * 0.3f, Path.Direction.CW)

        path.moveTo(centerX, centerY)
        path.rLineTo(0F, minRadius)
        path.moveTo(centerX, centerY)
        path.rLineTo(0F, -minRadius)
        path.moveTo(centerX, centerY)
        path.rLineTo(minRadius, 0F)
        path.moveTo(centerX, centerY)
        path.rLineTo(-minRadius, 0F)

        canvas?.drawPath(path, mainPaint)
    }
}