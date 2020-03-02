package com.bsvt.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Petal(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mainPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var power = defaultPower
    private var angle = defaultAngle
    private var directionCenter = defaultDirectionalityCenter
    private var color = defaultColor
    private var borderColor = defaultBorderColor
    private var border = defaultBorder
    private var margin = defaultMargin

    init {
        setupAttributes(context, attrs)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawPetal(canvas)
        drawBorder(canvas)
    }

    private fun drawPetal(canvas: Canvas?) {
        mainPaint.color = color
        mainPaint.style = Paint.Style.FILL

        drawBasePetal(canvas, paint = mainPaint)
    }

    private fun drawBorder(canvas: Canvas?) {
        mainPaint.color = borderColor
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = border

        drawBasePetal(canvas, mainPaint)
    }

    private fun drawBasePetal(canvas: Canvas?, paint: Paint) {
        val b = margin / sin(Math.toRadians((angle / 2).toDouble()))

        val centerCorrection = calculateCenter(directionCenter, angle, margin)

        val x = (width.toFloat() / 2) + centerCorrection[0]
        val y = (height.toFloat() / 2) + centerCorrection[1]
        val r = ((width.toFloat() / 2) - (margin + b)).toFloat()

        val oval = getOval(x, y, r * power)
        val startAngle = directionCenter - (angle / 2)
        canvas?.drawArc(oval, startAngle, angle, true, paint)
    }

    private fun setupAttributes(context: Context, attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.Petal, 0, 0)

        power = getPower(typedArray.getInteger(R.styleable.Petal_power, defaultPowerEnum))
        angle = getAngle(typedArray.getInteger(R.styleable.Petal_angle, defaultAngleEnum))
        directionCenter = getDirectionCenter(typedArray.getInteger(R.styleable.Petal_direction, defaultDirectionCenterEnum))
        color = typedArray.getColor(R.styleable.Petal_color, defaultColor)
        borderColor = typedArray.getColor(R.styleable.Petal_borderColor, defaultBorderColor)
        border = typedArray.getDimensionPixelSize(R.styleable.Petal_border, defaultBorder.toInt()).toFloat()
        margin = typedArray.getDimensionPixelSize(R.styleable.Petal_margin, defaultMargin.toInt()).toFloat()
    }

    private fun getAngle(enum: Int): Float {
        return when (enum) {
            0 -> 45f
            1 -> 22.5f
            else -> defaultAngle
        }
    }

    private fun getPower(enum: Int): Float {
        return when(enum) {
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
            else -> defaultPower
        }
    }

    private fun getDirectionCenter(enum: Int): Float {
        return when(enum) {
            0 -> -90f
            1 -> -67.5f
            2 -> -45f
            3 -> -22.5f
            4 -> 0f
            5 -> 22.5f
            6 -> 45f
            7 -> 67.5f
            8 -> 90f
            9 -> 112.5f
            10 -> 135f
            11 -> 157.5f
            12 -> 180f
            13 -> 202.5f
            14 -> 225f
            15 -> 247.5f
            else -> defaultDirectionalityCenter
        }
    }

    private fun getOval(x: Float, y: Float, r: Float): RectF {
        val oval = RectF()

        oval.set(x - r, y - r, x + r, y + r)

        return oval
    }

    private fun calculateCenter(startAngle: Float, angle: Float, margin: Float): Array<Float> {
        val normalAngle = if (startAngle >= 0) startAngle else 360 + startAngle

        val b = margin / sin(Math.toRadians((angle / 2).toDouble()))
        val x1 = (b * cos(Math.toRadians(normalAngle.toDouble()))).toFloat()
        val y1 = (b * sin(Math.toRadians(normalAngle.toDouble()))).toFloat()

        return arrayOf(x1, y1)
    }

    companion object {
        private var defaultPower = 1f
        private var defaultPowerEnum = 10


        private var defaultAngle = 22.5f
        private var defaultAngleEnum = 1

        private var defaultDirectionalityCenter = 0f
        private var defaultDirectionCenterEnum = 4


        private var defaultColor = Color.BLACK
        private var defaultBorderColor = Color.BLACK
        private var defaultBorder = 0f
        private var defaultMargin = 0f
    }
}