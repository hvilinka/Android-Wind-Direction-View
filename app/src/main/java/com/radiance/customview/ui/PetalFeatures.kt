package com.radiance.customview.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.larswerkman.lobsterpicker.OnColorListener

import com.radiance.customview.R
import kotlinx.android.synthetic.main.petal_features_fragment.*

class PetalFeatures : Fragment() {

    companion object {
        fun newInstance() = PetalFeatures()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.petal_features_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        petal_color.addOnColorListener( object : OnColorListener {

            override fun onColorChanged(color: Int) {
                sixteen.petalColor = color
            }

            override fun onColorSelected(color: Int) {
            }
        })

        petal_border_size.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sixteen.petalBorder = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        petal_border_color.addOnColorListener( object : OnColorListener {

            override fun onColorChanged(color: Int) {
                sixteen.petalBorderColor = color
            }

            override fun onColorSelected(color: Int) {
            }
        });
    }

}
