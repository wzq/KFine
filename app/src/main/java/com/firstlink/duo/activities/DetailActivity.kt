package com.firstlink.duo.activities

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.firstlink.duo.R
import com.firstlink.duo.fragments.DetailFragment

/**
 * Created by wzq on 15/12/22.
 */
class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRootView(R.layout.activity_detail)

        val color = ColorGenerator.MATERIAL.randomColor
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        }
        baseToolBar.setBackgroundColor(color)

        supportActionBar.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction().replace(R.id.detail_container, DetailFragment()).commit()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            finishAfterTransition()
        else
            finish()
        return super.onOptionsItemSelected(item)
    }

}