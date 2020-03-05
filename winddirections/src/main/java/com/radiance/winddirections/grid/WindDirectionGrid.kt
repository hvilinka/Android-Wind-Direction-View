package com.radiance.winddirections.grid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.radiance.winddirections.R

class WindDirectionGrid(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mainPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    var textSize = defaultTextSize
        set(value) {
            field = value
            draw()
        }
    var gridSize = defaultGridSize
        set(value) {
            field = value
            draw()
        }
    var gridColor = defaultGridColor
        set(value) {
            field = value
            draw()
        }
    var textColor = defaultTextColor
        set(value) {
            field = value
            draw()
        }

    init {
        setAttributes(context, attrs)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawGrid(canvas)
        drawText(canvas)
    }

    private fun drawGrid(canvas: Canvas?) {
        mainPaint.color = gridColor
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = gridSize

        val path = Path()

        val lineSize = (if (width <= height) width.toFloat() else height.toFloat()) / 2 * 0.9f
        val radius = (if (width <= height) width.toFloat() else height.toFloat()) / 2

        val centerX = width / 2f
        val centerY = height / 2f

        path.addCircle(centerX, centerY, radius * 0.9f, Path.Direction.CW)
        path.addCircle(centerX, centerY, radius * 0.6f, Path.Direction.CW)
        path.addCircle(centerX, centerY, radius * 0.3f, Path.Direction.CW)

        path.moveTo(centerX, centerY)
        path.rLineTo(0F, lineSize)
        path.moveTo(centerX, centerY)
        path.rLineTo(0F, -lineSize)
        path.moveTo(centerX, centerY)
        path.rLineTo(lineSize, 0F)
        path.moveTo(centerX, centerY)
        path.rLineTo(-lineSize, 0F)

        canvas?.drawPath(path, mainPaint)
    }

    private fun drawText(canvas: Canvas?) {
        mainPaint.style = Paint.Style.FILL_AND_STROKE
        mainPaint.textSize = textSize
        mainPaint.color = textColor

        val minRadius = (if (width <= height) width.toFloat() else height.toFloat()) / 2

        val xN = width / 2f - textSize / 3f
        val yN = (height / 2f) - minRadius + textSize
        canvas?.drawText("N", xN, yN, mainPaint)

        val xE = (width / 2f) + minRadius - textSize
        val yE = (height / 2f) + textSize / 3
        canvas?.drawText("E", xE, yE, mainPaint)

        val xS = width / 2f - textSize / 4f
        val yS = (height / 2f) + minRadius - (textSize / 5f)
        canvas?.drawText("S", xS, yS, mainPaint)


        val xW = (width / 2f) - minRadius + (textSize / 5f)
        val yW = (height / 2f) + textSize / 3
        canvas?.drawText("W", xW, yW, mainPaint)
    }

    private fun draw() {
        invalidate()
        requestLayout()
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.WindDirectionGrid, 0, 0
        )
        gridColor =
            typedArray.getColor(R.styleable.WindDirectionGrid_grid_color, defaultGridColor)
        textColor = typedArray.getColor(
            R.styleable.WindDirectionGrid_text_color,
            defaultTextColor
        )
        textSize = typedArray.getDimensionPixelSize(
            R.styleable.WindDirectionGrid_text_size,
            defaultTextSize.toInt()
        ).toFloat()

        gridSize = typedArray.getDimensionPixelSize(
            R.styleable.WindDirectionGrid_grid_size,
            defaultGridSize.toInt()
        ).toFloat()
    }

    companion object {
        var defaultTextSize = 40f
        var defaultGridSize = 1f
        var defaultGridColor = Color.BLACK
        var defaultTextColor = Color.BLACK
    }

}