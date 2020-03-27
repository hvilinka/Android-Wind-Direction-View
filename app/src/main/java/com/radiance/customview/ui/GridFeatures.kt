package com.radiance.customview.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.ColorInt
import com.larswerkman.lobsterpicker.OnColorListener

import com.radiance.customview.R
import com.radiance.customview.windDirections.petal.toTopStyle
import kotlinx.android.synthetic.main.grid_features_fragment.*

class GridFeatures : Fragment() {

    companion object {
        fun newInstance() = GridFeatures()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.grid_features_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sixteen.gridTextSize = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        textColor.addOnColorListener( object : OnColorListener {

            override fun onColorChanged(color: Int) {
                sixteen.gridTextColor = color
            }

            override fun onColorSelected(color: Int) {
            }
        })

        borderSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sixteen.gridLineSize = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        borderColor.addOnColorListener( object : OnColorListener {

            override fun onColorChanged(color: Int) {
                sixteen.gridLineColor = color
            }

            override fun onColorSelected(color: Int) {
            }
        });
    }

}
