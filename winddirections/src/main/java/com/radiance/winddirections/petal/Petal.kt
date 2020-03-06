package com.radiance.winddirections.petal

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import androidx.annotation.FloatRange
import com.radiance.winddirections.R
import com.radiance.customview.windDirections.petal.*
import kotlin.math.cos
import kotlin.math.sin

/**
 * View responsible for displaying one direction in a wind directions([com.radiance.winddirections.WindDirections])
 *
 * To display a petal in an activity, add a petal to the activity's layout XML file:
 *
 *      <com.radiance.winddirections.petal.Petal
 *          android:id="@+id/petal_id"
 *          android:layout_height="wrap_content"
 *          android:layout_width="wrap_content"/>
 *
 * All XML style attributes available on Petal:
 *
 *      background_color    - type: Color (petal color)
 *      border_color        - type: Color (petal border color)
 *      border_size         - type: Dimension (petal border size)
 *      margin              - type: Dimension (petal margin)
 *      petal_angle         - type: Enum, value: path_1_8, path_1_16
 *                            (petal angle size selection, path_1_8 = 45 dgr path_1_16 = 22.5 dgr)
 *      petal_direction     - type: Enum, value: N, NNE, NE, ENE, E, ESE, SE, SSE,
 *                                               S, SSW, SW, WSW, W, WNW, NW, NNW
 *                            (petal compass direction)
 *      petal_power         - type: Enum, value: percent_0, percent_10, percent_20, percent_30,
 *                                               percent_40, percent_50, percent_60, percent_70,
 *                                               percent_80, percent_90, percent_100
 *                            (petal size)
 *      petal_topStyle      - type: Enum, value: flat, sector (petal top style, flat - flat top,
 *                            sector - top is path of circle)
 *      petal_bottomStyle   - type: Enum, value: flat, sector (petal bottom style,
 *                            flat - flat bottom, sector - bottom is path of circle)
 *      petal_bottomRadius  - type: Dimension (radius of fillet of the petal bottom)
 *
 * @param context The Context the Petal is running in, through which it can
 *        access the current theme, resources, etc.
 * @param attrs The attributes of the XML Petal tag being used to inflate the view.
 *
 * @property power corresponds to petal_power attribute. Takes values for 0.0 to 1.0
 * @property angle corresponds to petal_angle attribute.
 * @property direction corresponds to petal_direction attribute.
 * @property color corresponds to background_color attribute.
 * @property borderColor corresponds to border_color attribute.
 * @property border corresponds to border_size attribute.
 * @property margin corresponds to margin attribute.
 * @property topStyle corresponds to petal_topStyle attribute.
 * @property bottomStyle corresponds to petal_bottomStyle attribute.
 * @property bottomRadius corresponds to petal_bottomRadius attribute.
 */
class Petal(context: Context, attrs: AttributeSet) : View(context, attrs) {
    //region attributes
    @FloatRange(from = 0.0, to = 1.0)
    var power = defaultPower
        set(value) {
            field = value
            updateView()
        }

    private var angle = Angle.Angle16
        set(value) {
            angleValue = value.toDegrees()
            field = value
            updateView()
        }

    private var direction = Direction.E
        set(value) {
            directionCenterValue = value.toDegrees()
            field = value
            updateView()
        }

    var color = defaultColor
        set(value) {
            field = value
            updateView()
        }

    var borderColor = defaultBorderColor
        set(value) {
            field = value
            updateView()
        }

    var border = defaultBorder
        set(value) {
            field = value
            updateView()
        }

    var margin = defaultMargin
        set(value) {
            field = value
            updateView()
        }

    var topStyle: TopStyle =
        defaultTopStyle
        set(value) {
            field = value
            updateView()
        }

    var bottomStyle =
        defaultBottomStyle
        set(value) {
            field = value
            updateView()
        }

    var bottomRadius = defaultBottomRadius
        set(value) {
            field = value
            updateView()
        }
    //endregion

    private var directionCenterValue = defaultDirectionalityCenter
    private var angleValue = defaultAngle

    init {
        setupAttributes(context, attrs)
    }

    private val mainPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (power > 0) {
            drawPetal(canvas)
            if (border > 0) {
                drawBorder(canvas)
            }
        }
    }

    private fun updateView() {
        invalidate()
        requestLayout()
    }

    private fun drawPetal(canvas: Canvas?) {
        mainPaint.color = color
        mainPaint.style = Paint.Style.FILL

        drawPetalShape(canvas, paint = mainPaint)
    }

    private fun drawBorder(canvas: Canvas?) {
        mainPaint.color = borderColor
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = border

        drawPetalShape(canvas, mainPaint)
    }

    private fun drawPetalShape(canvas: Canvas?, paint: Paint) {
        val b = margin / sin(Math.toRadians((angleValue / 2).toDouble()))
        val centerCorrection = calculateCenter(directionCenterValue, angleValue, margin)

        val x = (width.toFloat() / 2) + centerCorrection[0]
        val y = (height.toFloat() / 2) + centerCorrection[1]
        val minSize = if (width < height) width else height
        val r = (((minSize / 2) - (margin + b)).toFloat()) * power

        val path = Path()


        if (bottomRadius < r) {
            addTop(path, x, y, r, topStyle)
            addBottom(path, x, y, r, bottomStyle)
        }

        canvas?.drawPath(path, paint)
    }

    private fun addBottom(path: Path, x: Float, y: Float, r: Float, style: BottomStyle) {
        val startAngle = directionCenterValue - (angleValue / 2)
        val endAngle = directionCenterValue + (angleValue / 2)

        val leftStartX = r / 2 * cos(Math.toRadians(startAngle.toDouble()))
        val leftStartY = r / 2 * sin(Math.toRadians(startAngle.toDouble()))
        val leftEndX =  bottomRadius * cos(Math.toRadians(startAngle.toDouble()))
        val leftEndY = bottomRadius * sin(Math.toRadians(startAngle.toDouble()))
        val endX = r / 2 * cos(Math.toRadians(endAngle.toDouble()))
        val endY = r / 2 * sin(Math.toRadians(endAngle.toDouble()))
        val rightEndX = bottomRadius * cos(Math.toRadians(endAngle.toDouble()))
        val rightEndY = bottomRadius * sin(Math.toRadians(endAngle.toDouble()))

        val oval = getOval(x, y, bottomRadius)

        path.moveTo(x, y)
        path.rMoveTo(leftStartX, leftStartY)

        when (style) {
            BottomStyle.Flat -> {
                path.lineTo(x + leftEndX, y + leftEndY)
                path.lineTo(x + rightEndX, y + rightEndY)
            }
            BottomStyle.Sector -> {
                path.arcTo(oval, startAngle, angleValue)
            }
        }

        path.lineTo(x + endX, y + endY)
    }

    private fun addTop(path: Path, x: Float, y: Float, r: Float, style: TopStyle) {
        val startAngle = directionCenterValue - (angleValue / 2)
        val endAngle = directionCenterValue + (angleValue / 2)

        val startX = r / 2 * cos(Math.toRadians(startAngle.toDouble()))
        val startY = r / 2 * sin(Math.toRadians(startAngle.toDouble()))
        val leftCornerX = r * cos(Math.toRadians(startAngle.toDouble()))
        val leftCornerY = r * sin(Math.toRadians(startAngle.toDouble()))
        val rightCornerX = r * cos(Math.toRadians(endAngle.toDouble()))
        val rightCornerY = r * sin(Math.toRadians(endAngle.toDouble()))
        val endX = r / 2 * cos(Math.toRadians(endAngle.toDouble()))
        val endY = r / 2 * sin(Math.toRadians(endAngle.toDouble()))

        val oval = getOval(x, y, r)

        path.moveTo(x, y)
        path.rMoveTo(startX, startY)
        path.rLineTo(leftCornerX - startX, leftCornerY - startY)

        when (style) {
            TopStyle.Flat -> path.rLineTo(rightCornerX - leftCornerX, rightCornerY - leftCornerY)
            TopStyle.Sector -> path.arcTo(oval, startAngle, angleValue)
        }

        path.rLineTo(endX - rightCornerX, endY - rightCornerY)
    }

    private fun setupAttributes(context: Context, attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Petal, 0, 0
        )

        power = typedArray.getInteger(
            R.styleable.Petal_petal_power,
            defaultPowerEnum
        ).toPower()
        angle = typedArray.getInteger(
            R.styleable.Petal_petal_angle,
            defaultAngleEnum
        ).toAngleEnum()
        direction = typedArray.getInteger(
            R.styleable.Petal_petal_direction,
            defaultDirectionCenterEnum
        )
            .toDirection()

        color = typedArray.getColor(
            R.styleable.Petal_background_color,
            defaultColor
        )
        borderColor = typedArray.getColor(
            R.styleable.Petal_border_color,
            defaultBorderColor
        )
        border = typedArray.getDimensionPixelSize(R.styleable.Petal_border_size, defaultBorder.toInt())
            .toFloat()
        margin = typedArray.getDimensionPixelSize(R.styleable.Petal_margin, defaultMargin.toInt())
            .toFloat()
        topStyle =
            typedArray.getInteger(
                R.styleable.Petal_petal_topStyle,
                defaultTopStyleEnum
            ).toTopStyle()
        bottomStyle = typedArray.getInteger(
            R.styleable.Petal_petal_bottomStyle,
            defaultBottomStyleEnum
        )
            .toBottomStyle()
        bottomRadius = typedArray.getDimensionPixelSize(
            R.styleable.Petal_petal_bottomRadius,
            defaultBottomRadius.toInt()
        ).toFloat()
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
        private var defaultPower                = 1f
        private var defaultPowerEnum            = 10
        private var defaultAngle                = Angle.Angle16.toDegrees()
        private var defaultAngleEnum            = 1
        private var defaultDirectionalityCenter = 0f
        private var defaultDirectionCenterEnum  = 4
        private var defaultColor                = Color.BLACK
        private var defaultBorderColor          = Color.BLACK
        private var defaultBorder               = 0f
        private var defaultMargin               = 0f
        private var defaultTopStyle             = TopStyle.Flat
        private var defaultTopStyleEnum          = 0
        private var defaultBottomStyle          = BottomStyle.Flat
        private var defaultBottomStyleEnum      = 0
        private var defaultBottomRadius         = 0f
    }

    enum class Angle {
        Angle16,
        Angle8;

        fun toDegrees(): Float {
            return when (this) {
                Angle16 -> 22.5F
                Angle8 -> 45.0F
            }
        }
    }

    enum class Direction {
        N,
        NNE,
        NE,
        ENE,
        E,
        ESE,
        SE,
        SSE,
        S,
        SSW,
        SW,
        WSW,
        W,
        WNW,
        NW,
        NNW;

        fun toDegrees(): Float {
            return when (this) {
                N -> 270f
                NNE -> 292.5f
                NE -> 315f
                ENE -> 337.5f
                E -> 0f
                ESE -> 22.5f
                SE -> 45f
                SSE -> 67.5f
                S -> 90f
                SSW -> 112.5f
                SW -> 135f
                WSW -> 157.5f
                W -> 180f
                WNW -> 202.5f
                NW -> 225f
                NNW -> 247.5f
            }
        }
    }

    enum class TopStyle {
        Flat,
        Sector
    }

    enum class BottomStyle {
        Flat,
        Sector
    }
}