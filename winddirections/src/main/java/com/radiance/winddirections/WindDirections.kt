package com.radiance.winddirections

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.radiance.winddirections.grid.WindDirectionGrid
import com.radiance.winddirections.petal.Petal
import com.radiance.customview.windDirections.petal.toBottomStyle
import com.radiance.customview.windDirections.petal.toTopStyle
import com.radiance.customview.windDirections.petal.toWindAngle

/**
 * View responsible for displaying WindDirections
 *
 * To display a WindDirections in an activity, add a petal to the activity's layout XML file:
 *
 *      <com.radiance.winddirections.WindDirections
 *          android:id="@+id/wd_id"
 *          android:layout_height="wrap_content"
 *          android:layout_width="wrap_content"/>
 *
 * To set wind power:
 *  Programmatically:
 *      1. Create WindPower object. WindPower contains wind power depending on the direction.
 *         For all direction see [com.radiance.winddirections.WindDirections.WindPower]
 *
 *      val windPower = WindDirections.WindPower().apply {N = 400
 *                                                        S = 300
 *                                                        E = 200
 *                                                        W = 500}
 *
 *      2. Find windDirection view
 *
 *      val windDirections = findViewById<WindDirections>(R.id.wd_id)
 *
 *      3. Set windPower property
 *      windDirections.windPower = windPower
 *
 *  By XML:
 *      1. Please use programmatically gide
 *
 * All available XML style attributes:
 *
 *  WindDirections:
 *      directionsCount     - type: Enum, value: eight, sixteen (count of petal)
 *  Grid:
 *      grid_text_size      - type: Dimension (directions title text size)
 *      grid_text_color     - type: Color (direction title text color)
 *      grid_line_size      - type: Dimension (grid size)
 *      grid_line_color     - type: Color (grid color)
 *  Petal:
 *      petal_color         - type: Color (petal color)
 *      petal_border_color  - type: Color (petal border color)
 *      petal_border_size   - type: Dimension (petal border size)
 *      petal_margin        - type: Dimension (petal margin)
 *      petal_top_style     - type: Enum, value: flat, sector (petal top style, flat - flat top,
 *                            sector - top is path of circle)
 *      petal_bottom_style  - type: Enum, value: flat, sector (petal bottom style,
 *                            flat - flat bottom, sector - bottom is path of circle)
 *      petal_bottom_radius - type: Dimension (radius of fillet of the petal bottom)
 *
 * @param context The Context the WindDirections is running in, through which it can
 *        access the current theme, resources, etc.
 * @param attrs The attributes of the XML WindDirections tag being used to inflate the view.
 *
 * @property angle corresponds to directionsCount attribute.
 * @property windPower is used for display wind power
 * @property petalColor corresponds to petal_color attribute.
 * @property petalBorderColor corresponds to petal_border_color attribute.
 * @property petalBorder corresponds to petal_border_size attribute.
 * @property petalMargin corresponds to petal_margin attribute.
 * @property topStyle corresponds to petal_top_style attribute.
 * @property bottomStyle corresponds to petal_bottom_size attribute.
 * @property bottomRadius corresponds to petal_bottom_radius attribute.
 * @property gridTextColor corresponds to grid_text_color attribute.
 * @property gridTextSize corresponds to grid_text_size attribute.
 * @property gridLineColor corresponds to grid_line_color attribute.
 * @property gridLineSize corresponds to grid_line_size attribute.
 */
class WindDirections(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    //region attributes
    var angle: Angle = defaultAngle
        set(value) {
            field = value
            draw()
        }

    var windPower: WindPower = WindPower()
        set(value) {
            field = value
            windPowerPercent = value.toPercent()
            draw()
        }

    private var windPowerPercent = WindPower()

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
    //endregion

    private val petalList = ArrayList<Petal>()
    private var grid: WindDirectionGrid? = null
    private val petalRadiusCorrection = 0.9f

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        setAttributes(context, attrs)

        when (angle) {
            Angle.Eight -> inflater.inflate(R.layout.eight, this, true)
            Angle.Sixteen -> inflater.inflate(R.layout.sixteen, this, true)
        }

        getChildList()
        setChildStyle()
        setWindPower(petalRadiusCorrection)
    }

    fun startAnimation() {
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

    private fun setWindPower(correction: Float) {
        for (petal in petalList) {
            if (petal.id == R.id.N) {
                petal.power = windPowerPercent.N / 100f * correction
            }
            if (petal.id == R.id.NNE) {
                petal.power = windPowerPercent.NNE / 100f * correction
            }
            if (petal.id == R.id.NE) {
                petal.power = windPowerPercent.NE / 100f * correction
            }
            if (petal.id == R.id.ENE) {
                petal.power = windPowerPercent.ENE / 100f * correction
            }
            if (petal.id == R.id.E) {
                petal.power = windPowerPercent.E / 100f * correction
            }
            if (petal.id == R.id.ESE) {
                petal.power = windPowerPercent.ESE / 100f * correction
            }
            if (petal.id == R.id.SE) {
                petal.power = windPowerPercent.SE / 100f * correction
            }
            if (petal.id == R.id.SSE) {
                petal.power = windPowerPercent.SSE / 100f * correction
            }
            if (petal.id == R.id.S) {
                petal.power = windPowerPercent.S / 100f * correction
            }
            if (petal.id == R.id.SSW) {
                petal.power = windPowerPercent.SSW / 100f * correction
            }
            if (petal.id == R.id.SW) {
                petal.power = windPowerPercent.SW / 100f * correction
            }
            if (petal.id == R.id.WSW) {
                petal.power = windPowerPercent.WSW / 100f * correction
            }
            if (petal.id == R.id.W) {
                petal.power = windPowerPercent.W / 100f * correction
            }
            if (petal.id == R.id.WNW) {
                petal.power = windPowerPercent.WNW / 100f * correction
            }
            if (petal.id == R.id.NW) {
                petal.power = windPowerPercent.NW / 100f * correction
            }
            if (petal.id == R.id.NNW) {
                petal.power = windPowerPercent.NNW / 100f * correction
            }
        }
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.WindDirections, 0, 0
        )
        petalColor = typedArray.getColor(R.styleable.WindDirections_petal_color, defaultColor)
        petalBorderColor =
            typedArray.getColor(
                R.styleable.WindDirections_petal_border_color,
                defaultPetalBorderColor
            )
        petalBorder = typedArray.getDimensionPixelSize(
            R.styleable.WindDirections_petal_border_size,
            defaultPetalBorder.toInt()
        ).toFloat()
        petalMargin = typedArray.getDimensionPixelSize(
            R.styleable.WindDirections_petal_margin,
            defaultPetalMargin.toInt()
        ).toFloat()
        angle = typedArray.getInteger(R.styleable.WindDirections_directionsCount, defaultAngleEnum)
            .toWindAngle()
        topStyle =
            typedArray.getInteger(R.styleable.WindDirections_petal_top_style, defaultTopStyleEnum)
                .toTopStyle()
        bottomStyle =
            typedArray.getInteger(
                R.styleable.WindDirections_petal_bottom_style,
                defaultBottomStyleEnum
            )
                .toBottomStyle()
        bottomRadius = typedArray.getDimensionPixelSize(
            R.styleable.WindDirections_petal_bottom_radius,
            defaultBottomRadius.toInt()
        ).toFloat()

        gridLineColor =
            typedArray.getColor(R.styleable.WindDirections_grid_line_color,
                defaultGridColor
            )
        gridTextColor = typedArray.getColor(
            R.styleable.WindDirections_grid_text_color,
            defaultTextColor
        )
        gridTextSize = typedArray.getDimensionPixelSize(
            R.styleable.WindDirections_grid_text_size,
            defaultTextSize.toInt()
        ).toFloat()

        gridLineSize = typedArray.getDimensionPixelSize(
            R.styleable.WindDirections_grid_line_size,
            defaultGridSize.toInt()
        ).toFloat()
    }

    private fun draw() {
        setChildStyle()
        setWindPower(petalRadiusCorrection)
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
        private const val defaultPetalBorder = 1f
        private const val defaultPetalMargin = 0f
        private val defaultTopStyle = Petal.TopStyle.Sector
        private const val defaultTopStyleEnum = 1
        private val defaultBottomStyle = Petal.BottomStyle.Sector
        private const val defaultBottomStyleEnum = 1
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

    data class WindPower(
        var N: Int = 0,
        var NNE: Int = 15,
        var NE: Int = 30,
        var ENE: Int = 40,
        var E: Int = 60,
        var ESE: Int = 90,
        var SE: Int = 100,
        var SSE: Int = 60,
        var S: Int = 40,
        var SSW: Int = 0,
        var SW: Int = 0,
        var WSW: Int = 0,
        var W: Int = 0,
        var WNW: Int = 0,
        var NW: Int = 0,
        var NNW: Int = 0
    ) {
        fun toPercent(): WindPower {
            val max = getMaxPower()

            return WindPower(
                N * 100 / max,
                NNE * 100 / max,
                NE * 100 / max,
                ENE * 100 / max,
                E * 100 / max,
                ESE * 100 / max,
                SE * 100 / max,
                SSE * 100 / max,
                S * 100 / max,
                SSW * 100 / max,
                SW * 100 / max,
                WSW * 100 / max,
                W * 100 / max,
                WNW * 100 / max,
                NW * 100 / max,
                NNW * 100 / max
            )
        }

        private fun getMaxPower(): Int {
            return arrayOf(N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW).max()
                ?: 0
        }
    }
}