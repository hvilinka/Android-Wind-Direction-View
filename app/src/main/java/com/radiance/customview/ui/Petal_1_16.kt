package com.radiance.customview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.bsvt.winddirections.WindDirections
import com.radiance.customview.R
import com.bsvt.winddirections.petal.Petal
import com.radiance.customview.windDirections.petal.toBottomStyle
import com.radiance.customview.windDirections.petal.toTopStyle
import kotlinx.android.synthetic.main.fragment_petal_1_16.*
import kotlinx.android.synthetic.main.fragment_petal_1_16.borderSeek
import kotlinx.android.synthetic.main.fragment_petal_1_16.borderTitle
import kotlinx.android.synthetic.main.fragment_petal_1_16.bottomRadiusSeek
import kotlinx.android.synthetic.main.fragment_petal_1_16.bottomRadiusTitle
import kotlinx.android.synthetic.main.fragment_petal_1_16.bottomStyleSeek
import kotlinx.android.synthetic.main.fragment_petal_1_16.bottomStyleTitle
import kotlinx.android.synthetic.main.fragment_petal_1_16.marginSeek
import kotlinx.android.synthetic.main.fragment_petal_1_16.marginTitle
import kotlinx.android.synthetic.main.fragment_petal_1_16.topStyleSeek
import kotlinx.android.synthetic.main.fragment_petal_1_16.topStyleTitle
import kotlinx.android.synthetic.main.fragment_petal_1_8.*

class Petal_1_16 : Fragment() {
    private var topStyle = Petal.TopStyle.Sector
    private var bottomStyle = Petal.BottomStyle.Sector
    private var bottomRadius = 0
    private var margin = 1
    private var border = 1

    private var petals = ArrayList<Petal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_petal_1_16, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topStyleTitle.text = "${getString(R.string.topstyle)} (${styleString(topStyle)})"
        topStyleSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                topStyle = progress.toTopStyle()
                setStyle()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        bottomStyleTitle.text = "${getString(R.string.bottomstyle)} (${styleString(bottomStyle)})"
        bottomStyleSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                bottomStyle = progress.toBottomStyle()
                setStyle()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        bottomRadiusTitle.text = "${getString(R.string.bottomradius)} (${bottomRadius})"
        bottomRadiusSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                bottomRadius = progress
                setStyle()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        marginTitle.text = "${getString(R.string.margin)} (${margin})"
        marginSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                margin = progress
                setStyle()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        borderTitle.text = "${getString(R.string.border)} (${border})"
        borderSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                border = progress
                setStyle()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        setStyle()

        val windPower = WindDirections.WindPower().apply {
            N = 500
            NNE = 400
            NE = 500
            ENE = 440
            E = 550
            ESE = 300
            SE = 350
            SSE = 400
            S = 0
            SSW = 0
            SW = 0
            WSW = 0
            W = 0
            WNW = 0
            NW = 0
            NNW = 0
        }

        sixteen.windPower = windPower
    }

    @SuppressLint("SetTextI18n")
    private fun setStyle() {
        topStyleTitle.text = "${getString(R.string.topstyle)} (${styleString(topStyle)})"
        bottomStyleTitle.text = "${getString(R.string.bottomstyle)} (${styleString(bottomStyle)})"
        bottomRadiusTitle.text = "${getString(R.string.bottomradius)} (${bottomRadius})"
        marginTitle.text = "${getString(R.string.margin)} (${margin})"
        borderTitle.text = "${getString(R.string.border)} (${border})"

        sixteen.petalMargin = margin.toFloat()
        sixteen.petalBorder = border.toFloat()
        sixteen.topStyle = topStyle
        sixteen.bottomStyle = bottomStyle
        sixteen.bottomRadius = bottomRadius.toFloat()
    }

    private fun styleString(style: Petal.TopStyle): String {
        return when(style) {
            Petal.TopStyle.Flat -> getString(R.string.flat)
            Petal.TopStyle.Sector -> getString(R.string.sector)
        }
    }

    private fun styleString(style: Petal.BottomStyle): String {
        return when(style) {
            Petal.BottomStyle.Flat -> getString(R.string.flat)
            Petal.BottomStyle.Sector -> getString(R.string.sector)
        }
    }
}
