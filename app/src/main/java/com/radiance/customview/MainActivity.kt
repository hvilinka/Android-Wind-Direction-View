package com.radiance.customview

import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.radiance.customview.ui.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fragment_1_16 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_place, Petal_1_16(), null)
                    .commit()
                false
            }
            R.id.fragment_1_8 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_place, Petal_1_8(), null)
                    .commit()
                false
            }
            R.id.grid_features -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_place, GridFeatures(), null)
                    .commit()
                false
            }
            R.id.petal_features -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_place, PetalFeatures(), null)
                    .commit()
                false
            }
            R.id.margin -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_place, MarginBorderFeature(), null)
                    .commit()
                false
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_place, Petal_1_8(), null)
            .commit()
    }
}
