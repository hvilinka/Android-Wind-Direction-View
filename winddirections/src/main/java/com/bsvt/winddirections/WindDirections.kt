package com.bsvt.winddirections

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.radiance.customview.windDirections.petal.Petal
import com.radiance.customview.windDirections.petal.toBottomStyle
import com.radiance.customview.windDirections.petal.toTopStyle
import com.radiance.customview.windDirections.petal.toWindAngle

class WindDirections(context: Context, attrs: AttributeSet): FrameLayout(context, attrs) {
    private val childList = ArrayList<View>()
    var color = defaultColor
        set(value) {
            field = value
            draw()
        }

    var borderColor = defaultBorderColor
        set(value) {
            field = value
            draw()
        }

    var border = defaultBorder
        set(value) {
            field = value
            draw()
        }

    var margin = defaultMargin
        set(value) {
            field = value
            draw()
        }

    var angle: Angle = Angle.Sixteen
        set(value) {
            field = value
            draw()
        }

    var topStyle: Petal.TopStyle = defaultTopStyle
        set(value) {
            field = value
            draw()
        }

    var bottomStyle = defaultBottomStyle
        set(value) {
            field = value
            draw()
        }

    var bottomRadius = defaultBottomRadius
        set(value) {
            field = value
            draw()
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        setAttributes(context, attrs)

        when (angle) {
            Angle.Eight -> inflater.inflate(R.layout.eight, this, true)
            Angle.Sixteen -> inflater.inflate(R.layout.sixteen, this, true)
        }

        getChildList()
        setChildStyle()
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.WindDirections, 0, 0
        )
        color = typedArray.getColor(R.styleable.WindDirections_color, defaultColor)
        borderColor = typedArray.getColor(R.styleable.WindDirections_borderColor, defaultBorderColor)
        border = typedArray.getDimensionPixelSize(R.styleable.WindDirections_border, defaultBorder.toInt()).toFloat()
        margin = typedArray.getDimensionPixelSize(R.styleable.WindDirections_margin, defaultMargin.toInt()).toFloat()
        angle = typedArray.getInteger(R.styleable.WindDirections_directionsCount, 0).toWindAngle()
        topStyle = typedArray.getInteger(R.styleable.WindDirections_topStyle, defaultTopStyleEnum).toTopStyle()
        bottomStyle = typedArray.getInteger(R.styleable.WindDirections_bottomStyle, defaultBottomStyleEnum).toBottomStyle()
        bottomRadius = typedArray.getDimensionPixelSize(R.styleable.WindDirections_bottomRadius, defaultBottomRadius.toInt()).toFloat()
    }

    private fun getChildList() {
        for (i in 0 until childCount) {
            childList.add(getChildAt(i))
        }
    }

    private fun setChildStyle() {
        for (petal in childList) {
            if (petal is Petal) {
                petal.margin = margin
                petal.border = border
                petal.borderColor = borderColor
                petal.color = color
                petal.bottomStyle = bottomStyle
                petal.topStyle = topStyle
                petal.bottomRadius = bottomRadius
            }
        }
    }

    private fun draw() {
        setChildStyle()
        invalidate()
        requestLayout()
    }

    companion object {
        private var defaultColor = Color.BLACK
        private var defaultBorderColor = Color.BLACK
        private var defaultBorder = 0f
        private var defaultMargin = 0f

        private var defaultTopStyle =
            Petal.TopStyle.Flat
        private var defaultTopStyleEnum = 0

        private var defaultBottomStyle =
            Petal.BottomStyle.Flat
        private var defaultBottomStyleEnum = 0

        private var defaultBottomRadius = 0f
    }

    enum class Angle {
        Eight,
        Sixteen
    }

}