package com.radiance.customview.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.radiance.customview.Petal

import com.radiance.customview.R
import com.radiance.customview.externals.toBottomStyle
import com.radiance.customview.externals.toTopStyle
import kotlinx.android.synthetic.main.fragment_petal_1_16.*

class Petal_1_16 : Fragment() {
    private var topStyle = Petal.TopStyle.Flat
    private var bottomStyle = Petal.BottomStyle.Flat
    private var bottomRadius = 0
    private var margin = 0
    private var border = 0

    private var petals = ArrayList<Petal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_petal_1_16, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        petals.clear()
        petals.add(N)
        petals.add(NNE)
        petals.add(NE)
        petals.add(ENE)
        petals.add(E)
        petals.add(ESE)
        petals.add(SE)
        petals.add(SSE)
        petals.add(S)
        petals.add(SSW)
        petals.add(SW)
        petals.add(WSW)
        petals.add(W)
        petals.add(WNW)
        petals.add(NW)
        petals.add(NNW)
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
    }

    @SuppressLint("SetTextI18n")
    private fun setStyle() {
        topStyleTitle.text = "${getString(R.string.topstyle)} (${styleString(topStyle)})"
        bottomStyleTitle.text = "${getString(R.string.bottomstyle)} (${styleString(bottomStyle)})"
        bottomRadiusTitle.text = "${getString(R.string.bottomradius)} (${bottomRadius})"
        marginTitle.text = "${getString(R.string.margin)} (${margin})"
        borderTitle.text = "${getString(R.string.border)} (${border})"

        for (petal in petals) {
            petal.topStyle = topStyle
            petal.bottomStyle = bottomStyle
            petal.bottomRadius = bottomRadius.toFloat()
            petal.margin = margin.toFloat()
            petal.border = border.toFloat()
        }
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
