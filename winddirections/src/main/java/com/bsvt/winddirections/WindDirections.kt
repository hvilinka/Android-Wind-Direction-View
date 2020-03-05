package com.bsvt.winddirections


import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bsvt.winddirections.grid.WindDirectionGrid
import com.bsvt.winddirections.petal.Petal
import com.radiance.customview.windDirections.petal.toBottomStyle
import com.radiance.customview.windDirections.petal.toTopStyle
import com.radiance.customview.windDirections.petal.toWindAngle
class WindDirections(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private val petalList = ArrayList<Petal>()
    private var grid: WindDirectionGrid? = null

    var angle: Angle = defaultAngle
        set(value) {
            field = value
            draw()
        }

    var petalColor = defaultColor
        set(value) {
            field = value
            draw()
        }

    var petalBorderColor = defaultPetalBorderColor
        set(value) {
            field = value
            draw()
        }

    var petalBorder = defaultPetalBorder
        set(value) {
            field = value
            draw()
        }

    var petalMargin = defaultPetalMargin
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

    var gridTextSize = defaultTextSize
        set(value) {
            field = value
            draw()
        }
    var gridLineSize = defaultGridSize
        set(value) {
            field = value
            draw()
        }
    var gridLineColor = defaultGridColor
        set(value) {
            field = value
            draw()
        }
    var gridTextColor = defaultTextColor
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
        petalColor = typedArray.getColor(R.styleable.WindDirections_petal_color, defaultColor)
        petalBorderColor =
            typedArray.getColor(R.styleable.WindDirections_petal_border_color, defaultPetalBorderColor)
        petalBorder = typedArray.getDimensionPixelSize(
            R.styleable.WindDirections_petal_border_size,
            defaultPetalBorder.toInt()
        ).toFloat()
        petalMargin = typedArray.getDimensionPixelSize(
            R.styleable.WindDirections_petal_margin,
            defaultPetalMargin.toInt()
        ).toFloat()
        angle = typedArray.getInteger(R.styleable.WindDirections_directionsCount, defaultAngleEnum).toWindAngle()
        topStyle = typedArray.getInteger(R.styleable.WindDirections_petal_top_style, defaultTopStyleEnum)
            .toTopStyle()
        bottomStyle =
            typedArray.getInteger(R.styleable.WindDirections_petal_bottom_size, defaultBottomStyleEnum)
                .toBottomStyle()
        bottomRadius = typedArray.getDimensionPixelSize(
            R.styleable.WindDirections_petal_bottom_radius,
            defaultBottomRadius.toInt()
        ).toFloat()
    }

    private fun getChildList() {
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (view is Petal) {
                petalList.add(view)
            } else if (view is WindDirectionGrid) {
                grid = view
            }
        }
    }

    private fun setChildStyle() {
        for (petal in petalList) {
            petal.margin = petalMargin
            petal.border = petalBorder
            petal.borderColor = petalBorderColor
            petal.color = petalColor
            petal.bottomStyle = bottomStyle
            petal.topStyle = topStyle
            petal.bottomRadius = bottomRadius
        }
        grid?.textSize = gridTextSize
        grid?.textColor = gridTextColor
        grid?.gridColor = gridLineColor
        grid?.gridSize = gridLineSize
    }

    private fun draw() {
        setChildStyle()
        invalidate()
        requestLayout()
    }

    companion object {
        //wind directions
        private val defaultAngle = Angle.Sixteen
        private const val defaultAngleEnum = 0
        //petal
        private const val defaultColor = Color.BLACK
        private const val defaultPetalBorderColor = Color.BLACK
        private const val defaultPetalBorder = 0f
        private const val defaultPetalMargin = 0f
        private val defaultTopStyle = Petal.TopStyle.Flat
        private const val defaultTopStyleEnum = 0
        private val defaultBottomStyle = Petal.BottomStyle.Flat
        private const val defaultBottomStyleEnum = 0
        private const val defaultBottomRadius = 0f
        //grid
        private const val defaultTextSize = 40f
        private const val defaultGridSize = 1f
        private const val defaultGridColor = Color.BLACK
        private const val defaultTextColor = Color.BLACK
    }

    enum class Angle {
        Eight,
        Sixteen
    }

}