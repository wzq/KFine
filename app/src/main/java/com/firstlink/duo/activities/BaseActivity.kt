package com.firstlink.duo.activities

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.firstlink.duo.R
import kotlin.properties.Delegates

/**
 * Created by wzq on 15/12/21.
 */
open class BaseActivity : AppCompatActivity(){

    var baseToolBar : Toolbar by Delegates.notNull()
    var baseProgress : ProgressBar by Delegates.notNull()

    protected fun initRootView(resId : Int){
        setContentView(R.layout.view_base_linear)
        baseToolBar = findViewById(R.id.base_toolbar) as Toolbar
        baseProgress = findViewById(R.id.base_progressBar) as ProgressBar
        setSupportActionBar(baseToolBar)
        val container  =  findViewById(R.id.base_container) as FrameLayout
        container.addView(layoutInflater.inflate(resId, null))
    }
}