package com.firstlink.duo.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.amulyakhare.textdrawable.TextDrawable
import com.firstlink.duo.R

/**
 * Created by wzq on 15/12/22.
 */
class LaunchActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val logo = findViewById(R.id.launch_logo) as ImageView

        val mDrawableBuilder = TextDrawable.builder().beginConfig().withBorder(0).endConfig().round();

        logo.setImageDrawable(mDrawableBuilder.build(getString(R.string.app_name).substring(0, 2), ContextCompat.getColor(this, R.color.accent)))
//        startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
        logo.setOnClickListener {
            startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
        }
//        runDelayedOnUiThread(200, {
//            val mExplosionField = ExplosionField.attach2Window(this@LaunchActivity, {
//                startActivity(IntentFor<MainActivity>(this@LaunchActivity))
//                finish()
//            });
//            mExplosionField.explode(logo, 2000)
//        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        println("onRestoreInstanceState")
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        println("onSaveInstanceState")
    }
    override fun onStop() {
        super.onStop()
        println("onStop")
    }
    override fun onStart() {
        super.onStart()
        println("onStart")
    }
    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }
    override fun onResume() {
        super.onResume()
        println("onResume")
    }
    override fun onPause() {
        super.onPause()
        println("onPause")
    }
}