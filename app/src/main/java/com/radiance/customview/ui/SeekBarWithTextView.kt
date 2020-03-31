package com.radiance.customview.ui

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.radiance.customview.R

@RequiresApi(Build.VERSION_CODES.O)
class SeekBarWithTextView(
    context: Context,
    attributes: AttributeSet
) : LinearLayout(context, attributes) {

    var text: String = ""
        set(value) {
            field = value
            invalidate()
        }

    private var seekBar: SeekBar? = null

    var min: Int = 0
        @RequiresApi(Build.VERSION_CODES.O)
        set(value) {
            field = value
            seekBar?.min = value
            invalidate()
        }

    var max: Int = 5
        set(value) {
            field = value
            seekBar?.max = value
            invalidate()
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.seekbar_with_text_view, this, true)

        setAttributes(attributes, context)

        for (i in 0 until childCount) {
            val view = getChildAt(i)
            when (view.id) {
                R.id.tvTitleSeekBar -> (view as TextView).text = text
                R.id.seekBar ->  {
                    seekBar = view as SeekBar
                    seekBar?.min = min
                    seekBar?.max = max
                }
            }
        }
    }

    private fun setAttributes(attrs: AttributeSet, context: Context) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SeekBarWithTextView, 0, 0
        )

        text = typedArray.getString(R.styleable.SeekBarWithTextView_titleSeekBar) ?: text

        min = typedArray.getInteger(R.styleable.SeekBarWithTextView_min, 0)
        max = typedArray.getInteger(R.styleable.SeekBarWithTextView_max, 5)
    }

    fun setOnSeekBarChangeListener(l: OnSeekBarChangeListener) {
        seekBar?.setOnSeekBarChangeListener(l)
    }
}